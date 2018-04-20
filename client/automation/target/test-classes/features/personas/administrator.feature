@wip
Feature: Administrator persona (or role)

  Scenario: Add Users
    Given I login to the application as "administrator"
    When I add a new user
    Then the new user is displayed in the User List

  Scenario: Delete Users
    Given I login to the application as "administrator"
    When I delete an existing user
    Then the user is not displayed in the User List

  Scenario: Modify Users
    Given I login to the application as "administrator"
    When I modify an existing user
    Then the updated user information is displayed in the User List

  Scenario: Make conference room unavailable
    Given I login to the application as "administrator"
    When I select a conference room and mark it as "unavailable"
    Then the room availability is updated in the Conference Room List

  Scenario: Make conference room available
    Given I login to the application as "administrator"
    When I select a conference room and mark it as "available"
    Then the room availability is updated in the Conference Room List

  Scenario: Make equipment for a conference room unavailable
    Given I login to the application as "administrator"
    When I select a conference room
    And I mark its equipment as "unavailable"
    Then the equipment availability for the room is updated in the A/V List

  Scenario: Make equipment for a conference room available
    Given I login to the application as "administrator"
    When I select a conference room
    And I mark its equipment as "available"
    Then the equipment availability for the room is updated in the A/V List

  Scenario: Administrator successfully creates four succesful reservations
    Given I login to the application as "administrator"
    When I successfully create multiple reservations
    Then my reservations are displayed on the landing page

  Scenario: Administrator successfully modifies an existing reservation
    Given I login to the application as "administrator"
    And I successfully create a reservation
    When I update the reservation
    Then my updated reservation is displayed on the landing page

  @negative
  Scenario: Prevent reservations longer than 3 hours
    Given I login to the application as "administrator"
    When I attempt to create a reservation of illegal duration
    Then an error message is displayed
