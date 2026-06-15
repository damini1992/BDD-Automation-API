package stepdefination

import java.sql.ResultSet
import io.cucumber.java.en.*

import com.kms.katalon.core.configuration.RunConfiguration

import db.DatabaseUtils
import db.QueryManager
import excel.ExcelManager
import excel.ExcelUtils
import internal.GlobalVariable

import orange_UI.OrangeUtils

class OrangeHRMSteps {

    OrangeUtils orange
    String username
    String password
    List<Map<String, String>> users


@Given('the query data workbook is opened {string}')
def openWorkbook(String sheet1) {
		String path = RunConfiguration.getProjectDir() + "/Data Files/QueryData.xlsx"
		
        ExcelManager.openWorkbook(path, sheet1)
        orange = new OrangeUtils()
    }

@Given('valid login credentials are fetched from database {string}')
def fetchLoginCredentials(String getLogin) {

        String loginQuery = QueryManager.getQuery(getLogin)
println("Step1")
        ResultSet rs = DatabaseUtils.executeQuery(loginQuery)
		println("Step2")
		
        while (rs.next()) {
            username = rs.getString("username")
            password = rs.getString("password")
			println("username :"+username)
			
        }
    }

    @When('the user launches OrangeHRM application {string}')
def launchApplication(String screenshot1) {

        orange.openApplication(GlobalVariable.Orange_URL)
        orange.takeScreenshot(screenshot1)
    }

   @And('the user logs in using database credentials {string}')
def loginApplication(String screenshot2) {

        orange.login(username, password)
        orange.takeScreenshot(screenshot2)
    }

@And('the user navigates to the Admin page {string}')
def navigateToAdminPage(String screenshot3) {

        orange.navigateToAdminPage()
        orange.takeScreenshot(screenshot3)
    }

/*@Then('the user details should be inserted into the database {string}')
def updateDatabase(String getupdateQuery) {


        users = orange.getUserTableData()
    }*/

   @Then('the user details should be inserted into the database {string}')
def updateDatabase(String getupdateQuery) {

        users.each { user ->

            String queryUpdate =
                    QueryManager.getQuery(getupdateQuery)

            queryUpdate = String.format(
                    queryUpdate,
                    orange.escapeSql(user.username),
                    orange.escapeSql(user.role),
                    orange.escapeSql(user.employee),
                    orange.escapeSql(user.status)
            )

            DatabaseUtils.executeUpdate(queryUpdate)
        }
    }

   @Then('the user details should be written into the Excel file {string} {string} {string}')
def writeExcel(String userData, String employeeName, String userName) {

        List<Map<String, Object>> usersList =
                DatabaseUtils.getData(userData, employeeName,userName)

        String excelPath =
	RunConfiguration.getProjectDir() +
	"/Data Files/write.xlsx"

        ExcelUtils.writeDataToSheet(
                excelPath,
                GlobalVariable.EXCEL_SHEET,
                usersList
        )
    }

    @Then("all resources should be closed successfully")
    def closeResources() {

        DatabaseUtils.closeConnection()
        ExcelManager.closeWorkbook()
        orange.closeBrowser()
    }
}