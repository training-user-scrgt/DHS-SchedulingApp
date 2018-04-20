package gov.dhs.uscis.odos2.steps;

import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@Component
public class LoginSteps extends Steps {

	@Given("^I login to the application as \"([^\"]*)\"$")
	public void i_login_to_the_application_as(String persona) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the \"([^\"]*)\" landing page is displayed$")
	public void the_home_page_is_displayed(String persona) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
