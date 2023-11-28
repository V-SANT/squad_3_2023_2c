Feature: Accessing ticket in case they exist

  Scenario: No ticket in system.
    Given No tickets to access
    When Trying to get tickets
    Then I get no tickets

  Scenario: Some tickets.
    Given Two tickets
    When Trying to get tickets
    Then I get two tickets

  Scenario: Searches for existent ticket
    Given Ticket with name Ticket1 because of accesing
    When Trying to find ticket with name Ticket1
    Then I get the ticket appropriately

  Scenario: Searches for non-existent ticket
    Given Ticket with name MyTicket1 because of accesing
    When Trying to find ticket with name MyTicket2
    Then I get an Invalid Ticket Exception  because of accessing

