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

import java.util.List;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

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
        return ticketService.updateTicket(code, Optional.of(new_name), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketSeverity(Long code, Severity new_severity){
        return ticketService.updateTicket(code,Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(new_severity), Optional.empty(),  Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketPriority(Long code, Priority new_priority){
        return ticketService.updateTicket(code,Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(new_priority), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(), Optional.empty());
    }

    Ticket updateTicketInfo(Long code, String new_info){
        return ticketService.updateTicket(code,Optional.empty(), Optional.of(new_info), Optional.empty(), Optional.empty(), Optional.empty(),  Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketStatus(Long code, Status new_status){
        return ticketService.updateTicket(code, Optional.empty(), Optional.empty(), Optional.of(new_status), Optional.empty(), Optional.empty(),  Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketProduct(Long code, String new_product){
        return ticketService.updateTicket(code, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),  Optional.of(new_product) ,Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketVersion(Long code, String new_version){
        return ticketService.updateTicket(code, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),  Optional.empty() ,Optional.of(new_version),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
    }

     Ticket updateTicketClientID(Long code, Long new_client_id){
        return ticketService.updateTicket(code, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),  Optional.empty() ,Optional.empty(),Optional.of(new_client_id),Optional.empty(),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketEmployeeID(Long code, Long new_employee_id){
        return ticketService.updateTicket(code, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),  Optional.empty() ,Optional.empty(),Optional.empty(),Optional.of(new_employee_id),Optional.empty(),Optional.empty());
    }

    Ticket updateTicketEstimatedFinishDate(Long code, LocalDate new_date){
        return ticketService.updateTicket(code,Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(), Optional.of(new_date));
    }

    Ticket updateTicketAssociatedTask(Long code, List<Long> new_tasks){
         return ticketService.updateTicket(code,Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.of(new_tasks), Optional.empty());
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
