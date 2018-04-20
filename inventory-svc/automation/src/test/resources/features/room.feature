@smoke
Feature: Room Service
  As a user, I want to be able to activate and deactivate rooms
  so that users can have an up-to-date list of rooms to meet

  Scenario: Deactivate an existing meeting room
    Given I deactivate an existing meeting room
    Then the meeting room cannot be booked
