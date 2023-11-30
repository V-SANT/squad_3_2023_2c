Feature: Ticket editing
  Checking different ticket editing alternatives

  Scenario: Successfully edit the ticket status
    Given A ticket with status NOT STARTED
    When Trying to edit the status from NOT STARTED to IN PROGRESS
    Then Status is edited successfully
  
  Scenario: Successfully edit the ticket severity
    Given A ticket with severity S1
    When Trying to edit the severity from S1 to S2
    Then Severity is edited successfully

  Scenario: Successfully edit the ticket priority
    Given A ticket with priority LOW
    When Trying to edit the priority from LOW to HIGH
    Then Priority is edited successfully

  Scenario: Successfully edit the ticket info
    Given A ticket with an empty info
    When Trying to edit the info to a different one
    Then Info is edited successfully

  Scenario: Successfully edit the ticket name
    Given A ticket with name ticket1
    When Trying to edit the name from ticket1 to ticket2
    Then Name is edited successfully

  Scenario: Not Successfully edit the ticket name
    Given Two tickets with names ticket1 and ticket2
    When Trying to edit the name of ticket1 to the same name of ticket2
    Then I get a Ticket Name Already Taken Exception 
    And It remain with the original name
