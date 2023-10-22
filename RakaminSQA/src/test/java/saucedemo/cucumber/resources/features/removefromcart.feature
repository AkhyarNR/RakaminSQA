Feature: Remove selected product from cart

  Scenario: success removing product
    Given User login and on the dashboard
    When User go to cart
    And User remove the product
    Then The product will be removed