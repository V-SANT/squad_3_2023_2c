package com.aninfo.integration.cucumber;

import com.aninfo.Memo1TPG;
import com.aninfo.model.Ticket;
import com.aninfo.model.Severity;
import com.aninfo.model.Priority;
import com.aninfo.model.Status;
import com.aninfo.model.Employee;
import com.aninfo.service.TicketService;
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

    Ticket createTicket(String name) {
        return ticketService.createTicket(name, "", Status.NOT_STARTED, Severity.S1, Priority.LOW, new Employee(), LocalDate.now(), LocalDate.now());
    }

    Ticket createTicket(){
        return ticketService.createTicket("", "", Status.NOT_STARTED, Severity.S1, Priority.LOW, new Employee(), LocalDate.now(), LocalDate.now());
    }

    Ticket updateTicketName(Long code, String new_name){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,new_name, ticket.getInfo(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(), ticket.getEstimatedFinishDate());
    }

    Ticket updateTicketSeverity(Long code, Severity new_severity){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getName(), ticket.getInfo(), ticket.getStatus(), new_severity, ticket.getPriority(), ticket.getEstimatedFinishDate());
    }

    Ticket updateTicketPriority(Long code, Priority new_priority){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getName(), ticket.getInfo(), ticket.getStatus(), ticket.getSeverity(), new_priority, ticket.getEstimatedFinishDate());
    }

    Ticket updateTicketInfo(Long code, String new_info){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getName(), new_info, ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(), ticket.getEstimatedFinishDate());
    }

    Ticket updateTicketStatus(Long code, Status new_status){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code, ticket.getName(), ticket.getInfo(), new_status, ticket.getSeverity(), ticket.getPriority(), ticket.getEstimatedFinishDate());
    }

    Ticket updateTicketEstimatedFinishDate(Long code, LocalDate new_date){
        Ticket ticket = ticketService.findByCode(code);
        return ticketService.updateTicket(code,ticket.getName(), ticket.getInfo(), ticket.getStatus(), ticket.getSeverity(), ticket.getPriority(), new_date);
    }

    public Ticket findByCode(Long code){
        return ticketService.findByCode(code);
    }

    public Ticket findByName(String name){
        return ticketService.findByName(name);
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
}
