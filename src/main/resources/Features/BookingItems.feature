Feature: Booking Items

  Scenario: Add item valid case
    When User send valid request to add item
    Then user should receive the added item data

  Scenario: Get Item data valid case
      When Send valid request to get items list
      Then user should receive item data

    Scenario: Add Item Invalid cases
      When User send invalid request to add item with null total price
      When User send invalid request to add item with null deposit paid
      Then user should receive an error
