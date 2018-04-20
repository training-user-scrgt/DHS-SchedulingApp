package gov.uscis.odos2.steps;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.uscis.odos2.util.LoadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Component
public class APISteps extends Steps {

	@Given("^I make a reservation$")
	public void i_make_a_reservation_for_today() {
		RestAssured.baseURI = LoadProperties.getAPIURL();

		LocalDate today = LocalDate.now();
		LocalDate weekDay = getWeekDay(today);
		
		// Get date where there's no reservations on the selected date
		String formattedWeekDay = getDayWithNoReservations(weekDay);
		
		// We made sure there are no reservations on the selected date
		executionContext.getCurrentScenarioObj().addProperty("preTestReservationCount", 0);
		
		JsonObject reservationBody = new JsonObject();
		reservationBody.addProperty("reservationDate", formattedWeekDay);
		reservationBody.addProperty("startTime", "08:00");
		reservationBody.addProperty("endTime", "09:00");
		reservationBody.addProperty("conferenceTitle", "Automation_" + formattedWeekDay);
		reservationBody.addProperty("roomId", "1");

		given().body(reservationBody).contentType(ContentType.JSON).post().then().statusCode(201)
				.extract().response();
		// Note: if status is not 201, step will fail and so will scenario

		executionContext.getCurrentScenarioObj().addProperty("reservationDate", formattedWeekDay);
	}

	@Then("^my reservation can be retrieved$")
	public void my_reservation_can_be_retrieved() {
		RestAssured.baseURI = LoadProperties.getProperty("reservation.api.url");

		String reservationDate = executionContext.getCurrentScenarioObj().get("reservationDate").getAsString();

		int currentReservationCount = getReservationCountForDate(reservationDate);
		int preTestReservationCount = executionContext.getCurrentScenarioObj().get("preTestReservationCount").getAsInt();
		
		Assert.assertEquals("Reservations did not increase by one", (currentReservationCount - preTestReservationCount), 1);
	}

	/**
	 * Get JsonArray from RestAssured Response object
	 * 
	 * @param response
	 * @return
	 */
	public static JsonArray getJsonArray(Response response) {
		return getJsonElement(response.asString()).getAsJsonArray();
	}

	/**
	 * Return JSON object from string
	 * 
	 * @param jsonString
	 * @return
	 */
	public static JsonElement getJsonElement(String jsonString) {
		JsonParser parser = new JsonParser();
		return parser.parse(jsonString);
	}
	
	/**
	 * Reservation count for a given date
	 * 
	 * @param formattedDate e.g. 2018-04-20
	 * @return
	 */
	private int getReservationCountForDate(String formattedDate) {
		Response response = given().contentType(ContentType.JSON).pathParam("localDate", formattedDate).when()
				.get("/{localDate}").then().statusCode(200).extract().response();
		return response.jsonPath().getList("id").size();
	}
	
	/**
	 * Get a day of the week that avoids Sat and Sun
	 * 
	 * @param localDate
	 * @return
	 */
	private LocalDate getWeekDay(LocalDate localDate) {
		LocalDate newLocalDate = localDate.plusDays(1);
		
		switch (newLocalDate.getDayOfWeek()) {		
		case SATURDAY:
			newLocalDate = newLocalDate.plusDays(2);
			break;
		case SUNDAY:
			newLocalDate = newLocalDate.plusDays(1);
			break;
		default:
			// nothing to do
			break;
		}
		
		return newLocalDate;
	}
	
	/**
	 * Get a day without reservations to avoid issues with
	 * happy path test.
	 * 
	 * TODO Improve test so that it doesn't break in case
	 * there are no dates without reservations in the system
	 * 
	 * @param weekDay
	 * @return
	 */
	private String getDayWithNoReservations(LocalDate weekDay) {
		int tries = 0;
		int maxTries = 365;
		String formattedWeekDay = weekDay.format(DateTimeFormatter.ISO_DATE);
		while (tries < maxTries) {
			if (getReservationCountForDate(formattedWeekDay) != 0) {
				weekDay = getWeekDay(weekDay);
				formattedWeekDay = weekDay.format(DateTimeFormatter.ISO_DATE);
			} else {
				break;
			}
			tries++;
		}
		
		if (tries >= maxTries) {
			throw new RuntimeException("Unable to find week date without reservations in database");
		}
		
		return formattedWeekDay;
	}
}
