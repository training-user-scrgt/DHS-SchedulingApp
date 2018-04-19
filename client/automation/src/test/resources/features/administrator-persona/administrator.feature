@wip
Feature: Administrator persona (or role)
  As an Administrator, I shall be able to Add/Modify/Delete users,
  update conference rooms, and change the Audio/Visual equipment list

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
