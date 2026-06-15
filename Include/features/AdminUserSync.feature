Feature: Sync OrangeHRM Admin Users to Database and Excel

Background:
    Given the query data workbook is opened "Sheet1"

Scenario Outline: Extract users from OrangeHRM and update database

    Given valid login credentials are fetched from database "<GetLogin>"
    When the user launches OrangeHRM application "<screenshot1>"
    And the user logs in using database credentials "<screenshot2>"
    And the user navigates to the Admin page "<screenshot3>"
    Then the user details should be inserted into the database "<getupdateQuery>"
    Then the user details should be written into the Excel file "<userData>" "<employeeName>" "<userName>"
    And all resources should be closed successfully

Examples:
| GetLogin | sheet1 | screenshot1 | screenshot2 | screenshot3 | getupdateQuery | userData | employeeName | userName |
| GET_LOGIN | Sheet1 | Application Launched successfully. | Logged in with valid credentials. | Admin Page | INSERT_DATA | user_data | employee_name | username |