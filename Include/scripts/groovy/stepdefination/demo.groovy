package stepdefination
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.cucumber.java.en.And



class demo {
 /**
* The step definitions below match with Katalon sample Gherkin steps
*/
@Given("I want to launch google")
public void i_want_to_launch_google() {
	
	WebUI.openBrowser("https://automationexercise.com/")
    // Write code here that turns the phrase above into concrete actions
   // throw new PendingException();
}

 
}