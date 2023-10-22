Feature: User logout

  Scenario: Success logout
    Given User login and on the dashboard for logout
    When User open the navbar
    And User clicks logout
    Then User redirected to Login Page