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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TicketEditTest extends TicketIntegrationServiceTest {

    private Ticket ticket;
    private TicketTitleAlreadyTakenException name_error;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }
    
    @Given("^A ticket with status NOT STARTED$")
    public void ticket_with_status() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the status from NOT STARTED to IN PROGRESS$")
    public void trying_to_edit_status() {
        this.ticket = updateTicketStatus(this.ticket.getCode(),Status.IN_PROGRESS);
    }

    @Then("^Status is edited successfully$")
    public void status_edited_succesfully() {
        assertEquals(Status.IN_PROGRESS, this.ticket.getStatus());
    }

    @Given("^A ticket with severity S1$")
    public void ticket_with_severity() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the severity from S1 to S2$")
    public void trying_to_edit_severity() {
        this.ticket = updateTicketSeverity(this.ticket.getCode(), Severity.S2);
    }

    @Then("^Severity is edited successfully$")
    public void severity_edited_succesfully() {
        assertEquals(this.ticket.getSeverity(), Severity.S2);
    }

    @Given("^A ticket with priority LOW$")
    public void ticket_with_priority() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the priority from LOW to HIGH$")
    public void trying_to_edit_priority() {
        this.ticket = updateTicketPriority(this.ticket.getCode(), Priority.HIGH);
    }

    @Then("^Priority is edited successfully$")
    public void priority_edited_succesfully() {
        assertEquals(this.ticket.getPriority(), Priority.HIGH);
    }

    @Given("^A ticket with an empty info$")
    public void ticket_with_empty_info() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the info to a different one$")
    public void trying_to_edit_info() {
        this.ticket = updateTicketInfo(this.ticket.getCode(), "the info is not empty");
    }

    @Then("^Info is edited successfully$")
    public void info_edited_succesfully() {
        assertEquals(this.ticket.getDescription(), "the info is not empty");
    }

    @Given("^A ticket with name ticket1$")
    public void ticket_with_name() {
        this.ticket = createTicket("ticket1");
    }
    
    @When("^Trying to edit the name from ticket1 to ticket2$")
    public void trying_to_edit_name() {
        this.ticket = updateTicketName(this.ticket.getCode(), "ticket2");
    }
    
    @Then("^Name is edited successfully$")
    public void name_edited_succesfully() {
        assertEquals(this.ticket.getTitle(), "ticket2");
    }
    
    @Given("^Two tickets with names ticket1 and ticket2$")
    public void tickets_with_names() {
        this.ticket = createTicket("ticket1");
        createTicket("ticket2");
    }
    
    @When("^Trying to edit the name of ticket1 to the same name of ticket2$")
    public void trying_to_edit_repeated() {
        try {
            this.ticket = updateTicketName(this.ticket.getCode(), "ticket2");
        } catch (TicketTitleAlreadyTakenException name_error) {
            this.name_error = name_error;
        }
    }
    
    @Then("^I get a Ticket Name Already Taken Exception$")
    public void name_edited_no_succesfully() {
        assertNotNull(this.name_error);
    }
    
    @And("^It remain with the original name$")
    public void remain_same_name(){
        assertEquals(this.ticket.getTitle(), "ticket1");
    }

    @Given("^A ticket with product product1$")
    public void ticket_with_product() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the product from product1 to product2$")
    public void trying_to_edit_product() {
        this.ticket = updateTicketProduct(this.ticket.getCode(), "product2");
    }

    @Then("^Product is edited successfully$")
    public void product_edited_succesfully() {
        assertEquals(this.ticket.getProduct(), "product2");
    }

    @Given("^A ticket with version version1$")
    public void ticket_with_version() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the version from version1 to version2$")
    public void trying_to_edit_version() {
        this.ticket = updateTicketVersion(this.ticket.getCode(), "version2");
    }

    @Then("^Version is edited successfully$")
    public void version_edited_succesfully() {
        assertEquals(this.ticket.getVersion(), "version2");
    }


    @Given("^Given A ticket with clientID 0$")
    public void ticket_with_clientID() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the clientID from 0 to 1$")
    public void trying_to_edit_clientID() {
        this.ticket = updateTicketClientID(this.ticket.getCode(), 1L);
    }

    @Then("^ClientID is edited successfully$")
    public void clientID_edited_succesfully() {
        assertEquals(this.ticket.getClientId(), 1L);
    }


    @Given("^A ticket with employeeId 0$")
    public void ticket_with_employeeID() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the employeeId from 0 to 1$")
    public void trying_to_edit_employeeID() {
        this.ticket = updateTicketEmployeeID(this.ticket.getCode(), 1L);
    }

    @Then("^EmployeeID is edited successfully$")
    public void employeID_edited_succesfully() {
        assertEquals(this.ticket.getAssignatedEmployeeId(), 1L);
    }

    @Given("^ A ticket with closingDate today$")
    public void ticket_with_closingDate() {
        this.ticket = createTicket("ticket1");
    }

    @When("^Trying to edit the closingDate from today to tomorrow$")
    public void trying_to_edit_closingDate() {
        this.ticket = updateTicketEstimatedFinishDate(this.ticket.getCode(), LocalDate.now().plusDays(1));
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
