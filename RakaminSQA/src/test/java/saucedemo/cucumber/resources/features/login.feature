Feature: Login User to SauceDemo Dashboard

  Scenario: Success Login
    Given User in login page
    When Input username
    And Input password
    And Click login button
    Then User redirected to Dashboard


  Scenario: Failed Login
    Given User in login page
    When Input username
    And Input invalid password
    And Click login button
    Then User get error message