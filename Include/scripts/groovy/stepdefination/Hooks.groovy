package stepdefination
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import excelutils.ExcelReader
import helper.ScenarioContext
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
//import io.qameta.allure.Allure

//import helper.ScenarioContext
class Hooks {
@Before("@api")
    void BeforeScenario(Scenario scenario) {
        
		println("Starting Scenario: " + scenario.getName())
		
		
		ScenarioContext.clear()
 
			List<String> tags = scenario.getSourceTagNames().toList()
 
			String secondTag = ""
 
			if (tags.size() > 1) {
				secondTag = tags[1].replace("@", "")
			} else {
				secondTag = tags[0].replace("@", "")
			}
 
			KeywordUtil.logInfo("Excel Tag (2nd tag): " + secondTag)
 
			ScenarioContext.setContext("ScenarioTag", secondTag)
 
		
	}
 
 
    @After("@api")
    public void AfterScenario(Scenario scenario) {
		
		
		Allure.addAttachment("Execution", "Allure is working")
		
 
        KeywordUtil.logInfo("Finished scenario: " + scenario.getName() +
                " Status: " + scenario.getStatus())
 
        String status = scenario.getStatus().toString()
        String scenarioName = scenario.getName()
 
        // ✅ Get first tag (you can change if needed)
        String tag = scenario.getSourceTagNames().toList()[0].replace("@", "")
 
        println "Scenario: " + scenarioName
        println "Tag: " + tag
        println "Status: " + status
 
        // ✅ ✅ Dynamic path (NO hardcoding)
        String filePath = RunConfiguration.getProjectDir() + "/Data Files/Create New User2.xlsx"
 
        // ✅ Update Excel
        new ExcelReader().updateMultipleColumns(
                filePath,
                tag,
                status,
                ""
        )
 
        // ✅ Screenshot on failure
        if (scenario.isFailed()) {
            byte[] screenshot = WebUI.takeScreenshot()
            scenario.embed(screenshot, "image/png")
        }
 
        // ✅ Cleanup (Delete API call only if passed)
        if (!scenario.isFailed()) {
 
            def mv_email = ScenarioContext.getContext("mv_email")
            def mv_password = ScenarioContext.getContext("mv_password")
 
            if (mv_email && mv_password) {
                KeywordUtil.logInfo("Calling Delete User API for: " + mv_email)
 
                WS.sendRequestAndVerify(
                        findTestObject('Delete User', [
                                ('rv_email') : mv_email,
                                ('rv_password') : mv_password
                        ])
                )
            } else {
                KeywordUtil.logInfo("No user data found, skipping delete API")
            }
        }
		
		
		Allure.addAttachment("Scenario: " + scenario.getName(), "text/plain", "Executed")
    
    }
}