@smoke
Feature: Buildings Service
  As a user, I want to be able to add and remove buildings
  so that users can have an up-to-date list of places to meet

  Scenario: Add a building
    Given I add a new building to the building list
    Then the building can be retrieved
