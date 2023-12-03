package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.TicketTitleAlreadyTakenException;
import com.aninfo.model.Ticket;
import com.aninfo.model.Status;
import com.aninfo.model.Severity;
import com.aninfo.model.Priority;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.aninfo.service.TicketService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketWithTasksTest extends TicketIntegrationServiceTest {

    private Ticket ticket;
    private Collection<Long> associated_tickets;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
    
    @Given("^A ticket with task 0$")
    public void ticket_with_task() {
        this.ticket = createTicket("ticket1");
        List<Long> tasks = new ArrayList<>();
        tasks.add(0L);
        this.ticket.setAssociatedTasks(tasks);
    }

    @When("^Trying to get the tickets with task 1$")
    public void trying_to_edit_closingDate() {
        getTicketsAssociatedTask(1L);
    }

    @Then("^ClosingDate is edited successfully$")
    public void closing_date_edited_succesfully() {
        assertEquals(this.ticket.getClosingDate(), LocalDate.now().plusDays(1));
    }


    @After
    public void afterTest() {
        System.out.println("Resetting system");
        deleteAll();
    }

}
