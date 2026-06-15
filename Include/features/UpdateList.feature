Feature: Export data to Excel

  Scenario Outline: Fetch data from DB and write to Excel
    Given I fetch data from table "<tableName>" with columns "<columns>" and filter "<filterColumn>"="<filterValue>"

    Examples:
      | tableName | columns                                   | filterColumn | filterValue |
      | user_data | username,role_type,employee_name,status   | role_type    | Admin       |