package com.aninfo.integration.cucumber;

import com.aninfo.Memo1TPG;
import com.aninfo.model.Ticket;
import com.aninfo.model.Severity;
import com.aninfo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.util.List;

@ContextConfiguration(classes = Memo1TPG.class)
@WebAppConfiguration
public class TicketIntegrationServiceTest {

    @Autowired
    TicketService ticketService;

    Ticket createTicket(String name) {
        return ticketService.createTicket(name, "", Severity.S1, "", LocalDate.now(), LocalDate.now());
    }

    Ticket createTicket(){
        return ticketService.createTicket(name, "", Severity.S1, "", LocalDate.now(), LocalDate.now());
    }

    public Ticket findByCode(Long code){
        return ticketService.findByCode(code);
    }

    public Ticket findByName(String name){
        return ticketService.findByName(name);
    }

    public List<Ticket> findAll(){
        return ticketService.findAll();
    }

    public void deleteByCode(Long code){
        ticketService.deleteByCode(code);
    }
}
