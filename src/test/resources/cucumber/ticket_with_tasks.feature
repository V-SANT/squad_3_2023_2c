Feature: Ticket with tasks
  Checking different ticket with tasks

  Scenario: Successfully get no tickets associated to the id
    Given A ticket with task 0
    When Trying to get the tickets with task 1
    Then Get no tickets
  
  Scenario: Successfully get the tickets associated with task 0
    Given A ticket with task 0 and toher with task 1
    When Trying to get the tickets with task 0
    Then Get succesfuly the tickets
