@smoke
Feature: User Admin Service

  Scenario: Add a requestor
    Given I add a new requestor
    Then the new requestor can be retrieved

  Scenario: Delete a requestor
    Given I add a new requestor
    When I delete the requestor
    Then the requestor cannot be retrieved

  Scenario: Update a requestor
    Given I add a new requestor
    When I edit the requestor
    Then the edited requestor can be retrieved
