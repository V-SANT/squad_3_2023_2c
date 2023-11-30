package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.model.Ticket;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketSearchTest extends TicketIntegrationServiceTest {
    private Long code;
    private Ticket ticket;
    private InvalidTicketException invalid_error;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
    
    @Given("^A ticket$")
    public void ticket() {
        Ticket ticket = createTicket();
        this.code = ticket.getCode();
    }

    @Given("^No tickets to search$")
    public void no_tickets() {
        this.code = 1L;
    }

    @When("^Searching this ticket by it's code$")
    public void searching_ticket() {
        try {
            this.ticket = findByCode(this.code);
        } catch (InvalidTicketException invalid_error) {
            this.invalid_error = invalid_error;
        }
    }

    @Then("^It is found successfully$")
    public void it_is_found_successfully() {
        assertNull(this.invalid_error);
        assertNotNull(this.ticket);
    }

    @And("^The ticket's code matches$")
    public void ticket_code_matches() {
        assertEquals(this.code,this.ticket.getCode());
    }

    @Then("^I get an Invalid Ticket Exception because of searching$")
    public void obtains_invalid_ticket_exception() {
        assertNotNull(this.invalid_error);
        assertNull(this.ticket);
    }

    @After
    public void beforeEachTest() {
        System.out.println("Resetting system");
        invalid_error = null;
        ticket = null;
        deleteAll();
    }
}