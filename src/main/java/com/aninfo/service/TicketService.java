package com.aninfo.service;

import com.aninfo.exceptions.TicketTitleAlreadyTakenException;
import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.repository.TicketRepository;
import com.aninfo.model.Ticket;
import com.aninfo.model.Severity;
import com.aninfo.model.Status;
import com.aninfo.model.Priority;
import com.aninfo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String title, String info, Status status, Severity severity, Priority priority, String product, String version, Long employeeId, List<Long> associatedTasksIds, LocalDate startDate, LocalDate estimatedClosingDate) {
        ticketRepository.findTicketByTitle(title).ifPresent(x -> {throw new TicketTitleAlreadyTakenException("Title already taken");});
        Ticket ticket = new Ticket(title, info, status, severity, priority, product, version, employeeId, associatedTasksIds, startDate, estimatedClosingDate);
        return ticketRepository.save(ticket);
    }
    // public Ticket createTicket(Ticket ticket) {
    //     ticketRepository.findTicketByName(ticket.getName()).ifPresent(x -> {throw new TicketNameAlreadyTakenException("Name already taken");});
    //     // Ticket ticket = new Ticket(name, info, status, severity, priority, employeeId, associatedTasks, startDate, estimatedFinishDate);
    //     return ticketRepository.save(ticket);
    // }

    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findByCode(Long code) {
        return ticketRepository.findById(code).orElseThrow(() -> new InvalidTicketException("No ticket found with that code"));
    }

    public Ticket findByTitle(String title) {
        return ticketRepository.findTicketByTitle(title).orElseThrow(() -> new InvalidTicketException("No ticket found with that title"));
    }
    
    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }
    
    public void deleteByCode(Long code) {
        ticketRepository.deleteById(code);
    }

    public Ticket updateTicket(Long code, String title, String description, Status status, Severity severity, Priority priority, LocalDate date){
        Ticket ticket = ticketRepository.findById(code).orElseThrow(() -> new InvalidTicketException("No ticket found with that code"));
        ticketRepository.findTicketByTitle(title).ifPresent(x -> {
            if (!x.getCode().equals(code)) {
                throw new TicketTitleAlreadyTakenException("Title already taken");
            }
        });

        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setStatus(status);
        ticket.setSeverity(severity);
        ticket.setPriority(priority);
        ticket.setClosingDate(date);

        return ticketRepository.save(ticket);
    }

    public void deleteAll(){ ticketRepository.deleteAll(); }
    
}
