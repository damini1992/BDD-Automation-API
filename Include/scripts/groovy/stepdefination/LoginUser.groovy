package stepdefination
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import helper.ApiHelper
import helper.ScenarioContext

class LoginUser {

	TestData excelData = TestDataFactory.findTestData("Data Files/TD_Login_User")

	@Given("I Prepare test data file for sending login request")
	public void i_prepare_test_data_file_for_sending_login_request() {

		int rowCount = excelData.getRowNumbers()

		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {

			String rowTag = excelData.getValue("ScenarioTag", rowIndex)
			String tags = ScenarioContext.getContext("ScenarioTag")

			if (tags.contains(rowTag)) {

				ScenarioContext.setContext("mv_email", excelData.getValue("email", rowIndex))
				ScenarioContext.setContext("mv_password", excelData.getValue("password", rowIndex))
			}
		}
	}

	@When("I send request to Login API")
	public void i_send_request_to_login_api() {

		def response = WebUI.callTestCase(findTestCase('User Registration/Login User'), [
			('mv_email')    : ScenarioContext.getContext("mv_email"),
			('mv_password') : ScenarioContext.getContext("mv_password")
		], FailureHandling.STOP_ON_FAILURE)

		ScenarioContext.setContext("Response", response)
	}

	@Then("I verify the status code should be {int} for login api")
	public void i_verify_the_status_code_should_be(Integer expectedCode) {

		def response = ScenarioContext.getContext("Response")
		ApiHelper.validateStatusCode(response, 200)
	}

	@Then("I verify the message should be {string} for login api")
	public void i_verify_the_message_should_be(String expectedMessage) {

		def response = ScenarioContext.getContext("Response")
		ApiHelper.validateMessage(response, expectedMessage)
	}

	@Then("I verify the response contains authentication token")
	public void i_verify_response_contains_token() {

		def response = ScenarioContext.getContext("Response")
		WS.verifyElementPropertyValue(response, "token", WS.NOT_NULL)
	}

	@Then("I verify the response contains userId and email")
	public void i_verify_response_contains_user_details() {

		def response = ScenarioContext.getContext("Response")
		WS.verifyElementPropertyValue(response, "userId", WS.NOT_NULL)
		WS.verifyElementPropertyValue(response, "email", WS.NOT_NULL)
	}

	@Then("I verify the user session is created successfully")
	public void i_verify_session_created() {

		def response = ScenarioContext.getContext("Response")
		WS.verifyElementPropertyValue(response, "sessionId", WS.NOT_NULL)
	}

	// ✅ Validation-specific step
	@Then("I verify the message should be {string} for validation login")
	public void i_verify_validation_message(String expectedMessage) {

		def response = ScenarioContext.getContext("Response")
		WS.verifyElementPropertyValue(response, "message", expectedMessage)
	}
}