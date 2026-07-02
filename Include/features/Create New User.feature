Feature: User Creation
  I want to validate User Creation API

  @api @positive
  Scenario: Validation of User Creation API for successful user creation
    Given I Prepare test data file for sending request
    When I send request to Create New User API
    Then I verify the status code should be 200 for sucessful user creation
    And I verify the message should be "User Created!" for sucessful user creation
    And I verify response time should be less than 3000 mili seconds
    And  I validate schema against response  
    
  @api @negative
  Scenario: Validation of User Creation API for duplicate user
   Given I Prepare test data file for sending request
    When I send request to Create New User API
    Then I verify the status code should be 400
    And I verify the message should be "Email already exists!"
    
 @api @validation
  Scenario: Validation of User Creation API when mandatory fields are empty
   Given I Prepare test data file for sending request
    When I send request to Create New User API
    Then I verify the status code should be 400 for validation user creation
    And I verify the message should be "Email and password should not be empty" while creating user