package orange_UI

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.io.File
import java.util.Date

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import java.util.Date

class OrangeUtils {

    String rowsXpath =
            "//div[@class='oxd-table-body']//div[@role='row']"

    // ---------------- BROWSER ----------------

    def openApplication(String url) {

        WebUI.openBrowser(url)

        WebUI.maximizeWindow()

//        WebUI.navigateToUrl(url)
    }

    // ---------------- LOGIN ----------------

    def login(String username, String password) {

        WebUI.setText(
                findTestObject('Object Repository/LoginPage/txtBox_userName'),
                username)

        WebUI.setText(
                findTestObject('Object Repository/LoginPage/txtBox_password'),
                password)

        WebUI.click(
                findTestObject('Object Repository/LoginPage/button_submit'))
    }

    // ---------------- TITLE ----------------

    def getPageTitle() {

        return WebUI.getWindowTitle()
    }

    // ---------------- ADMIN PAGE ----------------

    def navigateToAdminPage() {

        WebUI.click(
                findTestObject('Object Repository/MainPage/button_admin'))
//		println("Step Start for admin")

//        WebUI.click(                findTestObject('Object Repository/MainPage/dd_UserRole'))
//		println("Step Start for UserRole")
    }

    // ---------------- GET TABLE DATA ----------------

    List<Map<String, String>> getUserTableData() {

        List<Map<String, String>> usersList = []

        TestObject tableRows = new TestObject()

        tableRows.addProperty(
                "xpath",
                ConditionType.EQUALS,
                rowsXpath)

        List<WebElement> rows =
                WebUI.findWebElements(tableRows, 10)

        for (WebElement row : rows) {

            String text = row.getText().trim()

            List<String> parts = text.split("\\r?\\n")

            if (parts.size() < 4) {
                continue
            }

            Map<String, String> userMap = [

                    username : parts[0].trim(),
                    role     : parts[1].trim(),
                    employee : parts[2].trim(),
                    status   : parts[3].trim()
            ]

            usersList.add(userMap)
        }

        return usersList
    }

    // ---------------- SQL ESCAPE ----------------

    String escapeSql(String value) {

        if (value == null) {

            return ""
        }

        return value.replace("'", "''")
    }

	// ---------------- TAKE SCREENSHOT ----------------
	def takeScreenshot(String screenshotName) {

    String timestamp =
            new Date().format("yyyyMMdd_HHmmss")

    // Current execution report folder
    String reportFolder =
            RunConfiguration.getReportFolder()

    println("Report Folder => " + reportFolder)

    // Fallback if report folder is null
    if (reportFolder == null) {

        reportFolder = System.getProperty("user.dir") +
                "/Reports/Screenshots"
    }

    // Create folder if not exists
    File folder = new File(reportFolder)

    if (!folder.exists()) {

        folder.mkdirs()
    }

    String fullPath =
            reportFolder + "/" +
            screenshotName + "_" +
            timestamp + ".png"

    WebUI.takeScreenshot(fullPath)

    println("Screenshot saved => " + fullPath)
}
		
def captureStep(String stepName) {
	
		WebUI.comment(stepName)
		WebUI.takeScreenshot()
	}
    // ---------------- CLOSE BROWSER ----------------

    def closeBrowser() {

        WebUI.closeBrowser()
    }
}