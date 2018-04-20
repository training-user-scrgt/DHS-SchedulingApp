@wip
Feature: Role-based Login
  As a user, when I login to the application
  I want to be presented with a landing page according to my persona

  Scenario: Login as Requester
    Given I login to the application as "requester"
    Then the "requester" landing page is displayed

  Scenario: Login as Reservation Manager
    Given I login to the application as "reservation manager"
    Then the "reservation manager" landing page is displayed

  Scenario: Login as Administrator
    Given I login to the application as "administrator"
    Then the "administrator" landing page is displayed
