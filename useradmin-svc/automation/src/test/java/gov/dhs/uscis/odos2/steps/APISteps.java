package gov.dhs.uscis.odos2.steps;

import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

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
}
