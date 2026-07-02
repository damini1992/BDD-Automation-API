import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriverException

import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

import io.cucumber.java.AfterStep
import io.cucumber.java.Scenario
import io.qameta.allure.Allure
import net.masterthought.cucumber.Configuration
import net.masterthought.cucumber.ReportBuilder
import net.masterthought.cucumber.json.support.Status

class TestListner {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
		
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		CucumberKW.GLUE = ['stepdefination']
	
		
	}
			/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		

		def timestamp = new Date().format("yyyy-MM-dd_HH-mm-ss")

		// ✅ Store JSON history folder
		def historyDirPath = "ReportsFolder/json-history"
		File historyDir = new File(historyDirPath)
		historyDir.mkdirs()

		// ✅ Move current JSON into history (if temp exists)
		File tempJson = new File("ReportsFolder/json-report/cucumber.json")

		if (tempJson.exists()) {
			File newHistoryFile = new File("${historyDirPath}/cucumber_${timestamp}.json")
			tempJson.renameTo(newHistoryFile)
			println("✅ JSON moved to history: " + newHistoryFile.name)
		}

		// ✅ Collect ALL historical JSON files
		List<String> jsonFiles = []
		historyDir.eachFile { file ->
			if (file.name.endsWith(".json")) {
				jsonFiles.add(file.absolutePath)
			}
		}

		println("📊 Total JSON files used: " + jsonFiles.size())

		// ✅ Single consistent output folder (no timestamp)
		def output = "ReportsFolder/cucumber-html/latest-report"
		File reportOutputDir = new File(output)
		reportOutputDir.mkdirs()

		// ✅ Configuration
		Configuration config = new Configuration(reportOutputDir, "Katalon v9 Consolidated Report")
		config.setNotFailingStatuses(Collections.singleton(Status.SKIPPED))
		config.addClassifications("Platform", "Windows")
		config.addClassifications("Branch", "release/1.0")
		config.addClassifications("Last Execution", timestamp)

		System.setProperty("file.encoding", "UTF-8")

		// ✅ Generate report using ALL history
		ReportBuilder builder = new ReportBuilder(jsonFiles, config)
		builder.generateReports()

		println("🎉 Consolidated history report generated at: " + reportOutputDir)
		
		
		String projectPath = RunConfiguration.getProjectDir()	   
	}
	
	
	@AfterTestCase
	   def afterTestCase(TestCaseContext context) {
	   }
   
}