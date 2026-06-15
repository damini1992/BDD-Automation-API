package helper
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil

import groovy.json.JsonSlurper
import stepdefination.Hooks



class ApiHelper {

	static List<String> errors = []

	// =========================
	// 1. STATUS CODE VALIDATION
	// =========================
	static void validateStatusCode(ResponseObject response, int expectedStatusCode) {

		def json = parseJsonSafely(response)
		def actualStatusCode = json?.responseCode


		if (actualStatusCode != expectedStatusCode) {
			String msg = "Status Code Failed:Expected is ${expectedStatusCode}, Actual is ${actualStatusCode}"

			KeywordUtil.markFailed(msg)
			errors.add(msg)               // store for final summary
			// 🔴 shows red in report
		} else {

			KeywordUtil.markPassed("Status code validated: ${actualStatusCode}")
			Hooks.scenario.write("Status code validated: " + String.valueOf(actualStatusCode))
			
		}
	}


	// =========================
	// 2. RESPONSE TIME VALIDATION
	// =========================
	static void validateResponseTime(ResponseObject response, long maxResponseTime) {

		long actualTime = response.getElapsedTime()

		if (actualTime > maxResponseTime) {
			String msg="Response time too high: ${actualTime/1000} s"
			KeywordUtil.markFailed(msg)
			errors.add(msg)
			// optional: fail test if you want strict performance
			// throw new AssertionError("Response time too high: ${actualTime} ms")
		}else {

			KeywordUtil.markPassed("Response Time: ${actualTime/1000} s")
			Hooks.scenario.write("Response Time: ${actualTime / 1000} s")
		}
	}



	// =========================
	// 3. MESSAGE VALIDATION
	// =========================
	static void validateMessage(ResponseObject response, String expectedMessage) {

		def json = parseJsonSafely(response)

		def actualMessage = json?.message

		String msg="""Message Validation Failed Expected : ${expectedMessage} Actual: ${actualMessage}"""

		if (actualMessage != expectedMessage) {
			KeywordUtil.markFailed(msg)
			errors.add(msg)
		}

		else {

			KeywordUtil.markPassed("Status code validated: ${actualMessage}")
			Hooks.scenario.write("Status code validated: ${actualMessage}")
		}
	}


	// =========================
	// 4. SAFE JSON PARSER
	// =========================
	private static def parseJsonSafely(ResponseObject response) {

		try {
			return new JsonSlurper().parseText(response.getResponseBodyContent())
		} catch (Exception e) {
			KeywordUtil.markWarning("Response is not valid JSON")
			return null
		}
	}
}
