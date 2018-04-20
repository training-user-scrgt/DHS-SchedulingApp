package gov.dhs.uscis.odos2.pageobjects;

import org.openqa.selenium.WebDriver;

import com.karsun.kic.tan.duke.ExecutionContext;
import com.karsun.kic.tan.duke.Page;

public class LoginPage extends Page {

	private ExecutionContext executionContext;

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage(ExecutionContext executionContext) {
		super(executionContext.getDriver());
		this.executionContext = executionContext;
	}

	@Override
	protected boolean isLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
}
