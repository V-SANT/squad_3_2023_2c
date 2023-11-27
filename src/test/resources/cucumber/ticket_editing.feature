Feature: Ticket editing
  Checking different ticket editing alternatives

  Scenario: Successfully edit a ticket
    Given a ticket with name ticket1
    When Trying to edit the ticket with name ticket1
    Then It is edited successfully

  Scenario: Cannot edit the name of a ticket to one that already exists
    Given Tickets with names ticket1 and ticket2
    When Trying to edit the name of ticket1 to ticket2
    Then It is not edited because a ticket with that name already exists

    #StandBy