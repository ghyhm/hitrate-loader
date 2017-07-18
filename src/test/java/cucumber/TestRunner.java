package cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/",
				 plugin = { "pretty", "html: cucumber-html-reports", "json: cucumber-html-reports/cucumber.json" },
				 glue = "cucumber.steps")
public final class TestRunner {
	private TestRunner() { }
}
