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

  Scenario: Successfully edit the ticket product
    Given A ticket with product product1
    When Trying to edit the product from product1 to product2
    Then Product is edited successfully

  Scenario: Successfully edit the ticket version
    Given A ticket with version version1
    When Trying to edit the version from version1 to version2
    Then Version is edited successfully

  Scenario: Successfully edit the clientID
    Given A ticket with clientID 0
    When Trying to edit the clientID from 0 to 1
    Then ClientID is edited successfully

  Scenario: Successfully edit the employeeId
    Given A ticket with employeeId 0
    When Trying to edit the employeeId from 0 to 1
    Then EmployeeId is edited successfully

   Scenario: Successfully edit the closingDate
    Given A ticket with closingDate today
    When Trying to edit the closingDate from today to tomorrow
    Then ClosingDate is edited successfully
