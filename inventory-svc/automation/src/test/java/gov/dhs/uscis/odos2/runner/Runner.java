package gov.dhs.uscis.odos2.runner;

import org.junit.runner.RunWith;

import com.karsun.kic.tan.duke.annotations.TestDataFiles;
import com.karsun.kic.tan.duke.cukes.MergedDataInjectedCucumber;

import cucumber.api.CucumberOptions;

@RunWith(MergedDataInjectedCucumber.class)
@CucumberOptions(plugin = { "json:build/test-result.json", "html:build/test-results" },
		tags = {"~@wip"},
		features = {
		"src/test/resources/features/"},
		glue={"com.karsun.kic.tan", "org.openqa", "gov.dhs.uscis.odos2"})
@TestDataFiles(files = { "src/test/resources/data/data.json" })
public class Runner {
}