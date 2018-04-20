package gov.dhs.uscis.odos2.steps;

import static io.restassured.RestAssured.given;

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
import cucumber.api.java.en.When;
import gov.dhs.uscis.odos2.util.DataUtils;
import gov.dhs.uscis.odos2.util.LoadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


@Component
public class APISteps extends Steps {

	@Given("^I add a new requestor$")
	public void i_add_a_new_requestor() {
		RestAssured.baseURI = LoadProperties.getProperty("user.api.url");

		JsonObject body = new JsonObject();
		body.addProperty("userName", DataUtils.getRandomString(10));
		body.addProperty("firstName", DataUtils.getRandomString(10));
		body.addProperty("lastName", DataUtils.getRandomString(10));
		executionContext.getCurrentScenarioObj().add("user", body);
		
		Response response = given().contentType(ContentType.JSON).body(body).when().post().then().statusCode(201).and().extract().response();
		executionContext.getCurrentScenarioObj().addProperty("Location", response.getHeader("Location"));
	}

	@Then("^the new requestor can be retrieved$")
	public void the_new_requestor_can_be_retrieved() {
		RestAssured.baseURI = LoadProperties.getProperty("user.api.url");
		
		String uuid = extractUUID(executionContext.getCurrentScenarioObj().get("Location").getAsString());
		
		Response response = given().when().get().then().statusCode(200).and().extract().response();
		
		List<String> ids = response.jsonPath().getList("id");
		Assert.assertTrue("ID for new user is not found", ids.contains(uuid));
	}

	@When("^I delete the requestor$")
	public void i_delete_the_requestor() {
		RestAssured.baseURI = LoadProperties.getProperty("user.api.url");
		String uuid = extractUUID(executionContext.getCurrentScenarioObj().get("Location").getAsString());
		given().when().delete(uuid).then().statusCode(200).and().extract().response();
	}

	@Then("^the requestor cannot be retrieved$")
	public void the_requestor_cannot_be_retrieved() {
		RestAssured.baseURI = LoadProperties.getProperty("user.api.url");
		
		String uuid = extractUUID(executionContext.getCurrentScenarioObj().get("Location").getAsString());
		
		Response response = given().when().get().then().statusCode(200).and().extract().response();
		
		List<String> ids = response.jsonPath().getList("id");
		Assert.assertFalse("User was not deleted", ids.contains(uuid));
	}

	@When("^I edit the requestor$")
	public void i_edit_the_requestor() {
		RestAssured.baseURI = LoadProperties.getProperty("user.api.url");

		JsonObject body = new JsonObject();
		body.addProperty("userName", DataUtils.getRandomString(10));
		body.addProperty("firstName", DataUtils.getRandomString(10));
		body.addProperty("lastName", DataUtils.getRandomString(10));
		executionContext.getCurrentScenarioObj().add("user", body);
		
		String uuid = extractUUID(executionContext.getCurrentScenarioObj().get("Location").getAsString());
		given().contentType(ContentType.JSON).body(body).when().put(uuid).then().statusCode(200).and().extract().response();
	}

	@Then("^the edited requestor can be retrieved$")
	public void the_edited_requestor_can_be_retrieved() {
		RestAssured.baseURI = LoadProperties.getProperty("user.api.url");
		
		String uuid = extractUUID(executionContext.getCurrentScenarioObj().get("Location").getAsString());
		Response response = given().when().get().then().statusCode(200).and().extract().response();
		
		JsonObject actualUserData = getUserData(response, uuid);
		Assert.assertNotNull("No data found for user " + uuid, actualUserData);
		
		JsonObject expectedUserData = executionContext.getCurrentScenarioObj().get("user").getAsJsonObject();
		
		Assert.assertNotEquals("User name", expectedUserData.get("userName"), actualUserData.get("userName"));
		Assert.assertEquals("First name", expectedUserData.get("firstName"), actualUserData.get("firstName"));
		Assert.assertEquals("Last name", expectedUserData.get("lastName"), actualUserData.get("lastName"));
	}

	/**
	 * Retrieve user data for a given UUID
	 * 
	 * @param response
	 * @param uuid
	 * @return JsonObject if found, null otherwise
	 */
	private JsonObject getUserData(Response response, String uuid) {
		JsonArray jsonArray = getJsonArray(response);
		for (JsonElement jsonElement : jsonArray) {
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			if (jsonObj.get("id").getAsString().equals(uuid)) {
				return jsonObj;
			}
		}
		return null;
	}
	
	public static JsonArray getJsonArray(Response response) {
		return getJsonElement(response.asString()).getAsJsonArray();
	}
	
	public static JsonElement getJsonElement(String jsonString) {
		JsonParser parser = new JsonParser();
		return parser.parse(jsonString);
	}

	private String extractUUID(String location) {
		String[] locArray = location.split("/");
		return locArray[locArray.length - 1];
	}
}
