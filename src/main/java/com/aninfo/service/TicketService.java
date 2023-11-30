package com.aninfo.service;

import com.aninfo.exceptions.TicketNameAlreadyTakenException;
import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.repository.TicketRepository;
import com.aninfo.model.Ticket;
import com.aninfo.model.Severity;
import com.aninfo.model.Status;
import com.aninfo.model.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String name, String info, Status status, Severity severity, Priority priority, String creator, LocalDate startDate, LocalDate estimatedFinishDate) {
        ticketRepository.findTicketByName(name).ifPresent(x -> {throw new TicketNameAlreadyTakenException("Name already taken");});
        Ticket ticket = new Ticket(name, info, status, severity, priority, creator, startDate, estimatedFinishDate);
        return ticketRepository.save(ticket);
    }

    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findByCode(Long code) {
        return ticketRepository.findById(code).orElseThrow(() -> new InvalidTicketException("No ticket found with that code"));
    }

    public Ticket findByName(String name) {
        return ticketRepository.findTicketByName(name).orElseThrow(() -> new InvalidTicketException("No ticket found with that name"));
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

    public Ticket updateTicket(Long code, String name, String info, Status status, Severity severity, Priority priority, LocalDate date){
        Ticket ticket = ticketRepository.findById(code).orElseThrow(() -> new InvalidTicketException("No ticket found with that code"));
        ticketRepository.findTicketByName(name).ifPresent(x -> {
            if (!x.getCode().equals(code)) {
                throw new TicketNameAlreadyTakenException("Name already taken");
            }
        });

        ticket.setName(name);
        ticket.setInfo(info);
        ticket.setStatus(status);
        ticket.setSeverity(severity);
        ticket.setPriority(priority);
        ticket.setEstimatedFinishDate(date);

        return ticketRepository.save(ticket);
    }

    public void deleteAll(){ ticketRepository.deleteAll(); }
    
}
