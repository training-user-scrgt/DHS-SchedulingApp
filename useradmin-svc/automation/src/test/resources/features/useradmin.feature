@smoke
Feature: User Admin Service

  Scenario: Add a user
    Given I add a new user
    Then the new user can be retrieved
