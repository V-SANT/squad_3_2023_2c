package com.aninfo.integration.cucumber;

import com.aninfo.Memo1TPG;
import com.aninfo.model.Ticket;
import com.aninfo.model.Severity;
import com.aninfo.model.Priority;
import com.aninfo.model.Status;
import com.aninfo.service.TicketService;
import com.aninfo.model.TicketCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.Collection;

@ContextConfiguration(classes = Memo1TPG.class)
@WebAppConfiguration
public class TicketIntegrationServiceTest {

    @Autowired
    TicketService ticketService;

    TicketCreationRequest createTicketCreationRequest(){
        TicketCreationRequest ticketCreationRequest = new TicketCreationRequest(
            "",
            "description",
            "No comenzado",
            "S1",
            "Alta",
            "product",
            "version",
            "1",
            "1",
            "2023-12-02"
        );
        return ticketCreationRequest;
    }

    Ticket createTicket(String title) {
        return ticketService.createTicket(title, "", Status.NOT_STARTED, Severity.S1,  Priority.LOW, "product1", "version1", 0L, 0L, LocalDate.now());
    }

    Ticket createTicket(){
        return ticketService.createTicket("", "", Status.NOT_STARTED, Severity.S1,  Priority.LOW, "", "", 0L, 0L, LocalDate.now());
    }

    Ticket updateTicketName(Long code, String new_name){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,new_name, ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(), ticket.getProduct(),ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }

    Ticket updateTicketSeverity(Long code, Severity new_severity){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), new_severity, ticket.getPriority(),  ticket.getProduct(),ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }

    Ticket updateTicketPriority(Long code, Priority new_priority){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), new_priority, ticket.getProduct(),ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(), ticket.getClosingDate());
    }

    Ticket updateTicketInfo(Long code, String new_info){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getTitle(), new_info, ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(),  ticket.getProduct(),ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }

    Ticket updateTicketStatus(Long code, Status new_status){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code, ticket.getTitle(), ticket.getDescription(), new_status, ticket.getSeverity(), ticket.getPriority(),  ticket.getProduct(),ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }

    Ticket updateTicketProduct(Long code, String new_product){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code, ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(),  new_product ,ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }

    Ticket updateTicketVersion(Long code, String new_version){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code, ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(),  ticket.getProduct() ,new_version,ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }


     Ticket updateTicketClientID(Long code, Long new_client_id){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code, ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(),  ticket.getProduct() ,ticket.getVersion(),new_client_id,ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(),ticket.getClosingDate());
    }


    Ticket updateTicketEmployeeID(Long code, Long new_employee_id){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code, ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(),  ticket.getProduct() ,ticket.getVersion(),ticket.getClientId(),new_employee_id,ticket.getAssociatedTasks(),ticket.getClosingDate());
    }

    Ticket updateTicketEstimatedFinishDate(Long code, LocalDate new_date){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(), ticket.getProduct(),ticket.getVersion(),ticket.getClientId(),ticket.getAssignatedEmployeeId(),ticket.getAssociatedTasks(), new_date);
    }

    public Ticket findByCode(Long code){
        return ticketService.findByCode(code);
    }

    public Ticket findByName(String name){
        return ticketService.findByTitle(name);
    }

    public Collection<Ticket> findAll(){
        return ticketService.findAll();
    }

    public void deleteByCode(Long code){
        ticketService.deleteByCode(code);
    }

    public void deleteAll(){
        ticketService.deleteAll();
    }

    public Collection<Long> getTicketsAssociatedTask(Long taskId){
        return ticketService.getTicketsAssociatedTask(taskId);
    }
}
