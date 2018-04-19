@wip
Feature: Display Panel

  Scenario: Daily schedule display for a room
    Given a person looks (or activates) a panel screen by a conference room
    Then the screen displays today’s schedule for that Conference Room
