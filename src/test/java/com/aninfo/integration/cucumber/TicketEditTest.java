package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.exceptions.TicketNameAlreadyTakenException;
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
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketEditTest extends TicketIntegrationServiceTest {

    private Ticket ticket;
    private Collection<Ticket> allTickets;
    private InvalidTicketException invalid_error;
    private TicketNameAlreadyTakenException name_error;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Resetting system");
        invalid_error = null;
        deleteAll();
    }

    @Given("^A ticket with status NOT STARTED$")
    public void ticket_with_status() {
        this.ticket = createTicket("ticket1");
        this.ticket.setStatus(Status.NOT_STARTED);
        this.allTickets = findAll();
    }

    @When("^Trying to edit the status from NOT STARTED to IN PROGRESS$")
    public void trying_to_edit_status() {
        updateTicketStatus(this.ticket.getCode(),Status.IN_PROGRESS);
    }

    @Then("^Status is edited successfully$")
    public void status_edited_succesfully() {
        assertEquals(this.ticket.getStatus(), Status.IN_PROGRESS);
    }

    @Given("^A ticket with severity S1$")
    public void ticket_with_severity() {
        this.ticket = createTicket("ticket1");
        this.allTickets = findAll();
    }

    @When("^Trying to edit the severity from S1 to S2$")
    public void trying_to_edit_severity() {
        updateTicketSeverity(this.ticket.getCode(), Severity.S2);
    }

    @Then("^Severity is edited successfully$")
    public void severity_edited_succesfully() {
        assertEquals(this.ticket.getSeverity(), Severity.S2);
    }

    @Given("^A ticket with priority LOW$")
    public void ticket_with_priority() {
        this.ticket = createTicket("ticket1");
        this.allTickets = findAll();
    }

    @When("^Trying to edit the priority from LOW to HIGH$")
    public void trying_to_edit_priority() {
        updateTicketPriority(this.ticket.getCode(), Priority.HIGH);
    }

    @Then("^Priority is edited successfully$")
    public void priority_edited_succesfully() {
        assertEquals(this.ticket.getPriority(), Priority.HIGH);
    }

    @Given("^A ticket with an empty info$")
    public void ticket_with_empty_info() {
        this.ticket = createTicket("ticket1");
        this.allTickets = findAll();
    }

    @When("^Trying to edit the info to a different one$")
    public void trying_to_edit_info() {
        updateTicketInfo(this.ticket.getCode(), "the info is not empty");
    }

    @Then("^Info is edited successfully$")
    public void info_edited_succesfully() {
        assertEquals(this.ticket.getInfo(), "the info is not empty");
    }

    @Given("^A ticket with name ticket1$")
    public void ticket_with_name() {
        this.ticket = createTicket("ticket1");
        this.allTickets = findAll();
    }

    @When("^Trying to edit the name from ticket1 to ticket2$")
    public void trying_to_edit_name() {
        updateTicketName(this.ticket.getCode(), "ticket2");
    }

    @Then("^Name is edited successfully$")
    public void name_edited_succesfully() {
        assertEquals(this.ticket.getName(), "ticket2");
    }

    @Given("^Two tickets with names ticket1 and ticket2$")
    public void tickets_with_names() {
        this.ticket = createTicket("ticket1");
        createTicket("ticket2");
        this.allTickets = findAll();
    }

    @When("^Trying to edit the name of ticket1 to the same name of ticket2$")
    public void trying_to_edit_repeated() {
        try {
            this.ticket = updateTicketName(this.ticket.getCode(), "ticket2");
        } catch (TicketNameAlreadyTakenException name_error) {
            this.name_error = name_error;
        }
    }

    @Then("^I get a Ticket Name Already Taken Exception$")
    public void name_edited_no_succesfully() {
        assertNotNull(this.invalid_error);
    }

    @And("^It remain with the original name$")
    public void remain_same_name(){
        assertEquals(this.ticket.getName(), "ticket1");
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }

}
