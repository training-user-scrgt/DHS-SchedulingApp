package gov.uscis.odos2.steps;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

	@Given("^I make a reservation for today$")
	public void i_make_a_reservation_for_today() {
		RestAssured.baseURI = LoadProperties.getProperty("reservation.api.url");

		LocalDate today = LocalDate.now();
		String formattedDate = today.format(DateTimeFormatter.ISO_DATE);
		
		// Save current reservation counts
		int reservationCount = getReservationCountForDate(formattedDate);
		executionContext.getCurrentScenarioObj().addProperty("preTestReservationCount", reservationCount);

		JsonObject reservationBody = new JsonObject();
		reservationBody.addProperty("reservationDate", formattedDate);
		reservationBody.addProperty("startTime", "09:00");
		reservationBody.addProperty("endTime", "10:00");
		reservationBody.addProperty("conferenceTitle", "Automation_" + formattedDate);
		reservationBody.addProperty("roomId", "1");

		given().body(reservationBody).contentType(ContentType.JSON).post().then().statusCode(201)
				.extract().response();
		// Note: if status is not 201, step will fail and so will scenario

		executionContext.getCurrentScenarioObj().addProperty("reservationDate", formattedDate);
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
	
	private int getReservationCountForDate(String formattedDate) {
		Response response = given().contentType(ContentType.JSON).pathParam("localDate", formattedDate).when()
				.get("/{localDate}").then().statusCode(200).extract().response();
		return response.jsonPath().getList("id").size();
	}
}
