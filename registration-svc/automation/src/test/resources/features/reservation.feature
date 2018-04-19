@smoke
Feature: Reservation
  As a user, I want to reserve a room
  so that I can hold it for later use

  Scenario: Make a reservation
    Given I make a reservation for today
    Then my reservation can be retrieved