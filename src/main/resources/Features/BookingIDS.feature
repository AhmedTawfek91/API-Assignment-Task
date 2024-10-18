Feature: BookingIds

  Scenario: Get all booking Ids valid case
    When user send a valid request to get all booking Ids
    Then user receive booking Ids successfully
