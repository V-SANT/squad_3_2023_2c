package com.aninfo.integration.cucumber;

import com.aninfo.model.Ticket;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketWithTasksTest extends TicketIntegrationServiceTest {

    private Ticket ticket;
    private Ticket ticket2;
    private Collection<Long> associated_tickets;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
    
    @Given("^A ticket with task 0$")
    public void ticket_with_task_zero() {
        this.ticket = createTicket("ticket1");
        List<Long> tasks = new ArrayList<>();
        tasks.add(0L);
        this.ticket = updateTicketAssociatedTask(this.ticket.getCode() ,tasks);
    }

    @When("^Trying to get the tickets with task 1$")
    public void trying_to_get_tickets_with_task_one() {
        this.associated_tickets = getTicketsAssociatedTask(1L);
        // List<Long> ticketsIds = new ArrayList<Long>();
        // // ticketsIds.add(1L);
        // this.associated_tickets = ticketsIds;
    }

    @Then("^Get no tickets$")
    public void get_no_tickets_with_task_zero() {
        assertEquals(this.associated_tickets.size(), 0);
    }

    @Given("^A ticket with task 0 and toher with task 1$")
    public void tickets_with_differents_tasks() {
        this.ticket = createTicket("ticket1");
        this.ticket2 = createTicket("ticket2");
        List<Long> tasks = new ArrayList<>();
        tasks.add(0L);
        List<Long> tasks2 = new ArrayList<>();
        tasks2.add(1L);
        this.ticket.setAssociatedTasks(tasks);
        this.ticket2.setAssociatedTasks(tasks2);
    }

    @When("^Trying to get the tickets with task 0$")
    public void trying_to_get_the_tickets() {
        this.associated_tickets = getTicketsAssociatedTask(0L);
    }

    @Then("^Get succesfuly the tickets$")
    public void get_succesfuly_the_ticket() {
        assertEquals(this.associated_tickets.size(), 1);
    }


    @After
    public void afterTest() {
        System.out.println("Resetting system");
        deleteAll();
    }

}
