Feature: User Login
  I want to validate User Login API

  @positive
  Scenario: Validation of Login API for successful login
    Given I Prepare test data file for sending login request
    When I send request to Login API
    Then I verify the status code should be 200 for login api
    And I verify the message should be "User exists!" for login api

  @negative
  Scenario: Validation of Login API response for invalid credential
    Given I Prepare test data file for sending login request
    When I send request to Login API
    Then I verify the status code should be 404 for login api
    And I verify the message should be "User not found!" for login api

  @validation
  Scenario: Validation of Login API when email and password are empty
    Given I Prepare test data file for sending login request
    When I send request to Login API
    Then I verify the status code should be 404 for login api
    And I verify the message should be "email should not be empty" for login api

