package gov.dhs.uscis.odos2.steps;

import static io.restassured.RestAssured.given;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gov.dhs.uscis.odos2.util.LoadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.*;

@Component
public class APISteps extends Steps {
	
	@Given("^I add a new building to the building list$")
	public void i_add_a_new_building_to_the_building_list() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the building can be retrieved$")
	public void the_building_can_be_retrieved() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	@Given("^I deactivate an existing meeting room$")
	public void i_deactivate_an_existing_meeting_room() throws Throwable {
	    RestAssured.baseURI = LoadProperties.getAPIURL("room.api.url");
	    
	    Response response = given().contentType(ContentType.JSON).get().then().statusCode(200).and().extract().response();
	    int roomCount = getJsonArray(response).size();
	    executionContext.getCurrentScenarioObj().addProperty("roomCount", roomCount);
	    
	    JsonObject firstRoom = getJsonArray(response).get(0).getAsJsonObject();
	    Assert.assertNotNull("No rooms found in response!", firstRoom);
	    
	    // Deactive the room
	    firstRoom.remove("available");
	    firstRoom.addProperty("available", false);
	    String roomId = firstRoom.get("roomId").getAsString();
	    given().body(firstRoom).contentType(ContentType.JSON).post("/" + roomId).then().statusCode(201).and().extract().response();
	    executionContext.getCurrentScenarioObj().add("room", firstRoom);
	}

	@Then("^the meeting room cannot be booked$")
	public void the_meeting_room_cannot_be_booked() throws Throwable {
		Response response = given().contentType(ContentType.JSON).get().then().statusCode(200).and().extract().response();
		
		int currentRoomCount = getJsonArray(response).size();
		int previousRoomCount = executionContext.getCurrentScenarioObj().get("roomCount").getAsInt();
		Assert.assertEquals("Room reservations did not decrease by one", (previousRoomCount - currentRoomCount), 1);
		
		// Reactivate the room
		JsonObject room = executionContext.getCurrentScenarioObj().get("room").getAsJsonObject();
		room.remove("available");
		room.addProperty("available", true);
	    String roomId = room.get("roomId").getAsString();
	    given().body(room).contentType(ContentType.JSON).post("/" + roomId).then().statusCode(201);
	}
	
	public static JsonArray getJsonArray(Response response) {
		return getJsonElement(response.asString()).getAsJsonArray();
	}
	
	public static JsonElement getJsonElement(String jsonString) {
		JsonParser parser = new JsonParser();
		return parser.parse(jsonString);
	}
}
