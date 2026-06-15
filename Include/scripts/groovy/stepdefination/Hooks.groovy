package stepdefination

import io.cucumber.java.Before
import io.cucumber.java.After
import io.cucumber.java.Scenario

class Hooks {

    @Before
    void BeforeScenario(Scenario scenario) {
        println("Starting Scenario: " + scenario.getName())
    }

    @After
    void AfterScenario(Scenario scenario) {
        println("Ending Scenario: " + scenario.getName())
    }
}