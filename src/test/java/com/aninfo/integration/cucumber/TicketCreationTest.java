package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.TicketNameAlreadyTakenException;
import com.aninfo.model.Ticket;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketCreationTest extends TicketIntegrationServiceTest {

    private Ticket ticket;
    private TicketNameAlreadyTakenException name_error;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Resetting system");
        this.deleteAll();
    }

    @Given("^No ticket with name (.*)$")
    public void no_ticket_with_name(String name) {
    }

    @Given("^Ticket with name (.*) because of creating$")
    public void ticket_with_name(String name) {
        createTicket(name);
    }
    
    @When("^Trying to create a ticket with name (.*)$")
    public void trying_create_ticket(String name)  {
        try {
            this.ticket = createTicket(name);
        } catch (TicketNameAlreadyTakenException name_error){
            this.name_error = name_error;
        }
    }

    @Then("^It is created successfully$")
    public void ticket_created_successfully() {
        assertNull(this.name_error);
        assertNotNull(this.ticket);
    }

    @Then("^It is not created, ticket with that name already exists$")
    public void ticket_not_created_successfully() {
        assertNotNull(this.name_error);
        assertNull(this.ticket);
    }

    @And("^Ticket is named (.*)$")
    public void ticket_named(String name) {
        assertEquals(name, this.ticket.getName());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
