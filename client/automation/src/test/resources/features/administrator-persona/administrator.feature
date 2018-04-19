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
    