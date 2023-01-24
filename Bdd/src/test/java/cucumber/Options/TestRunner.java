package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", glue={"StepDefinitions"})
//, tags= "@DeletePlace"
public class TestRunner {
}
