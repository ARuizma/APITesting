package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","json:target/jsonReports/cucumber-report.json"},
        features="src/test/java/features",
        glue={"StepDefinitions"},
        stepNotifications = true
)
//, tags= "@DeletePlace"
public class TestRunner {
}
