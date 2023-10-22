Feature: User checkout a product

  Scenario: success checkout
    Given User login and on the dashboard for checkout
    When User select a product
    And User add to cart
    Then User check the cart