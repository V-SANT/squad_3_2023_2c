package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.exceptions.TicketNameAlreadyTakenException;
import com.aninfo.model.Ticket;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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
public class TicketAccessTest extends TicketIntegrationServiceTest {

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
        ticket = null;
        deleteAll();
    }

    @Given("^No tickets$ to access")
    public void no_tickets() {
    }

    @When("^Trying to get tickets$")
    public void trying_to_get_tickets() {
        this.allTickets = findAll();
    }

    @Then("^I get no tickets$")
    public void no_tickets_gotten() {
        assertTrue(this.allTickets.isEmpty());
    }

    @Given("^Two tickets$")
    public void two_tickets() {
        createTicket("ticket1");
        createTicket("ticket2");
        this.allTickets = findAll();
    }

    @Then("^I get two tickets$")
    public void two_tickets_gotten() {
        assertEquals(this.allTickets.size(), 2);
    }

    @Given("^Ticket with name (.*) because of accesing$")
    public void ticket_with_a_name(String name) {
        createTicket(name);
    }

    @Given("^Ticket with name (.*) is deleted$")
    public void ticket_with_a_name_deleted(String name) {
        Ticket ticket = createTicket(name);
        deleteByCode(ticket.getCode());
        this.ticket = null;
    }

    @When("^Trying to find ticket with name (.*)$")
    public void trying_to_find_ticket(String name) {
        try {
            this.ticket = findByName(name);
        } catch (InvalidTicketException invalid_error) {
            this.invalid_error = invalid_error;
        }
    }

    @Then("^I get the ticket appropriately$")
    public void obtains_ticket_appropriately() {
        assertNotNull(this.ticket);
        assertNull(this.invalid_error);
    }

    @Then("^I get an Invalid Ticket Exception because of accessing$")
    public void obtains_invalid_ticket_exception() {
        assertNull(this.ticket);
        assertNotNull(this.invalid_error);
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}