package stepdefination

import org.junit.runner.RunWith
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
	features = "Include/features/Create New User.feature",
	glue = "stepdefination",
	plugin = [
		"pretty",
		"json:ReportsFolder/json-report/cucumber.json",
		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	]
)
public class RunCucumberTest {

}
