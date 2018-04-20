@wip
Feature: Reservation Manager persona (or role)

  Scenario: Export conference report as JPG
    Given I login to the application as "reservation manager"
    When I access the conference room report
    Then I can export the data as format "JPG"

  Scenario: Export conference report as CSV
    Given I login to the application as "reservation manager"
    When I access the conference room report
    Then I can export the data as format "CSV"

  Scenario: Export conference report as PNG
    Given I login to the application as "reservation manager"
    When I access the conference room report
    Then I can export the data as format "PNG"

  Scenario: Export conference report as PDF
    Given I login to the application as "reservation manager"
    When I access the conference room report
    Then I can export the data as format "PDF"

  Scenario: Reservation manager successfully creates four succesful reservations
    Given I login to the application as "reservation manager"
    When I successfully create multiple reservations
    Then my reservations are displayed on the landing page

  Scenario: Reservation manager successfully modifies an existing reservation
    Given I login to the application as "reservation manager"
    And I successfully create a reservation
    When I update the reservation
    Then my updated reservation is displayed on the landing page

  @negative
  Scenario: Prevent reservations longer than 3 hours
    Given I login to the application as "reservation manager"
    When I attempt to create a reservation of illegal duration
    Then an error message is displayed
