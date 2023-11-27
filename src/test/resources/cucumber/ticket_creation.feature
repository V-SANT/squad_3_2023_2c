Feature: Ticket creation
  Checking different ticket creation alternatives

  Scenario: Successfully create a ticket
    Given No ticket with name ticket1
    When Trying to create a project with name ticket1
    Then It is created successfully
    And Ticket is named ticket1


  Scenario: Cannot create a ticket with existing name
    Given Ticket with name ticket1
    When Trying to create a ticket with name ticket1
    Then It is not created because a ticket with that name already exists