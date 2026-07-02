package stepdefination

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import helper.ApiHelper
import helper.ScenarioContext
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.qameta.allure.Allure


public class CreateNewUser {

	TestData excelData = TestDataFactory.findTestData("Data Files/TD_Create_New_User")

	@Given("I Prepare test data file for sending request")
	public void i_prepare_test_data_file_for_sending_request() {


		int rowCount = excelData.getRowNumbers()

		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {

			String rowTag = excelData.getValue("ScenarioTag", rowIndex)
			
			println rowTag

			String tags=ScenarioContext.getContext("ScenarioTag")
			
			println tags

			if (tags.contains(rowTag)) {

				ScenarioContext.setContext("mv_Name", excelData.getValue("name", rowIndex))
				ScenarioContext.setContext("mv_email", excelData.getValue("email", rowIndex))
				ScenarioContext.setContext("mv_password",excelData.getValue("password", rowIndex))
				ScenarioContext.setContext("mv_title", excelData.getValue("title", rowIndex))
				ScenarioContext.setContext("mv_birth_date",excelData.getValue("birth_date", rowIndex))
				ScenarioContext.setContext("mv_birth_month",excelData.getValue("birth_month", rowIndex))
				ScenarioContext.setContext("mv_birth_year",excelData.getValue("birth_year", rowIndex))
				ScenarioContext.setContext("mv_firstname",excelData.getValue("firstname", rowIndex))
				ScenarioContext.setContext("mv_lastname",excelData.getValue("lastname", rowIndex))
				ScenarioContext.setContext("mv_company",excelData.getValue("company", rowIndex))
				ScenarioContext.setContext("mv_address1",excelData.getValue("address1", rowIndex))
				ScenarioContext.setContext("mv_address2",excelData.getValue("address2", rowIndex))
				ScenarioContext.setContext("mv_country",excelData.getValue("country", rowIndex))
				ScenarioContext.setContext("mv_zipcode",excelData.getValue("zipcode", rowIndex))
				ScenarioContext.setContext("mv_State",excelData.getValue("state", rowIndex))
				ScenarioContext.setContext("mv_city",excelData.getValue("city", rowIndex))
				ScenarioContext.setContext("mv_mobile_number",excelData.getValue("mobile_number", rowIndex))
			}
		}
	}

	@When("I send request to Create New User API")
	public void i_send_request_to_create_new_user_api() {

		def response = WebUI.callTestCase(findTestCase('User Registration/Sign Up New User'), [
			('mv_name')         : ScenarioContext.getContext("mv_Name"),
			('mv_email')        : ScenarioContext.getContext("mv_email"),
			('mv_password')     : ScenarioContext.getContext("mv_password"),
			('mv_title')        : ScenarioContext.getContext("mv_title"),
			('mv_birth_date')   : ScenarioContext.getContext("mv_birth_date"),
			('mv_birth_month')  : ScenarioContext.getContext("mv_birth_month"),
			('mv_birth_year')   : ScenarioContext.getContext("mv_birth_year"),
			('mv_firstname')    : ScenarioContext.getContext("mv_firstname"),
			('mv_lastname')     : ScenarioContext.getContext("mv_lastname"),
			('mv_company')      : ScenarioContext.getContext("mv_company"),
			('mv_address1')     : ScenarioContext.getContext("mv_address1"),
			('mv_address2')     : ScenarioContext.getContext("mv_address2"),
			('mv_country')      : ScenarioContext.getContext("mv_country"),
			('mv_zipcode')      : ScenarioContext.getContext("mv_zipcode"),
			('mv_state')        : ScenarioContext.getContext("mv_state"),
			('mv_city')         : ScenarioContext.getContext("mv_city"),
			('mv_mobile_number'): ScenarioContext.getContext("mv_mobile_number")
		], FailureHandling.STOP_ON_FAILURE)


		String responseBody = response.getResponseBodyContent()

		
        Allure.step("Response Body")
		def jsonObject = new JsonSlurper().parseText(responseBody)
		def prettyJson = JsonOutput.prettyPrint(JsonOutput.toJson(jsonObject))
		Allure.step(prettyJson)

		ScenarioContext.setContext("Response", response)
		ScenarioContext.setContext("mv_email", ScenarioContext.getContext("mv_email"))
		ScenarioContext.setContext("mv_passsword", ScenarioContext.getContext("mv_password"))
	}

	@Then("I verify the status code should be 200 for sucessful user creation")
	public void i_verify_the_status_code_should_be() {

		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateStatusCode(response, 200)
	}

	@Then("I verify the message should be {string} for sucessful user creation")
	public void i_verify_the_message_should_be_for_sucessful_user_creation(String string) {
		// Write code here that turns the phrase above into concrete actions
		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateMessage(response, string)
	}

	@Then("I verify response time should be less than {int} mili seconds")
	public void i_verify_response_time_should_be_less_than_mili_seconds(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateResponseTime(response, int1)
	}

	@Then("I validate schema against response")
	public void i_validate_schema_against_response() {
		// Write code here that turns the phrase above into concrete actions
		ResponseObject response=ScenarioContext.getContext("Response")
		WS.validateJsonAgainstSchema(response, "Include/Resources/Create New User.json", FailureHandling.STOP_ON_FAILURE)
	}

	@Then("I verify the status code should be {int}")
	public void i_verify_the_status_code_should_be(Integer int1) {
		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateStatusCode(response, int1)
	}

	@Then("I verify the message should be {string}")
	public void i_verify_the_message_should_be(String string) {
		// Write code here that turns the phrase above into concrete actions
		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateMessage(response, string)
	}

	@Then("I verify the status code should be {int} for validation user creation")
	public void i_verify_the_status_code_should_be_for_validation_user_creation(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateStatusCode(response, int1)
	}

	@Then("I verify the message should be {string} while creating user")
	public void i_verify_the_message_should_be_while_creating_user(String string) {
		// Write code here that turns the phrase above into concrete actions
		ResponseObject response=ScenarioContext.getContext("Response")
		ApiHelper.validateMessage(response, string)
	}
}
