Feature: Finding existing tickets

  Scenario: Can find an existing ticket
    Given A ticket
    When Searching this ticket by it's code
    Then It is found successfully
    And The tickets's code matches
    
  Scenario: Can't find an existing ticket
    Given no tickets to search
    When Searching a ticket by it's code
    Then I get an Invalid Ticket Exception because of searching
