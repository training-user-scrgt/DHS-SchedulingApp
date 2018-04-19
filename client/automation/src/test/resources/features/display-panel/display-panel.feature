@wip
Feature: Display Panel
  For anyone, the display panel (vendors are only required to simulate data payload) are outside of the conference room
  shall present/display conference scheduled for that room and day in the format of "0000-0000 Conference Title Last name".
  The display panel shall utilize API for the query service for updates

  Scenario: Daily schedule display for a room
    Given a person looks (or activates) a panel screen by a conference room
    Then the screen displays today’s schedule for that Conference Room
