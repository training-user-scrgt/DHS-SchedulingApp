@wip
Feature: Requestor persona (or role)

  Scenario: Requestor successfully creates four succesful reservations
    Given I login to the application as "requestor"
    When I successfully create multiple reservations
    Then my reservations are displayed on the landing page

  Scenario: Requestor successfully modifies an existing reservation
    Given I login to the application as "requestor"
    And I successfully create a reservation
    When I update the reservation
    Then my updated reservation is displayed on the landing page

  @negative
  Scenario: Prevent double-booking by requestor
    Given I login to the application as "requestor"
    And I successfully create a reservation
    When I attempt to double-book the room
    Then an error message is displayed

  @negative
  Scenario: Prevent reservations longer than 3 hours
    Given I login to the application as "requestor"
    When I attempt to create a reservation of illegal duration
    Then an error message is displayed
