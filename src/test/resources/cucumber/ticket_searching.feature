Feature: Finding existing tickets

  Scenario: Can find an existing ticket
    Given A ticket
    When Searching this ticket by it's id
    Then It is found successfully
    And The tickets's id matches
    
  Scenario: Can't find an existing ticket
    Given no ticket
    When Searching a ticket by it's id
    Then It is not found
    And get a Invalid Ticket Exception
