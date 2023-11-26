package com.aninfo.service;

import com.aninfo.exceptions.TicketNameAlreadyTakenException;
import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.repository.TicketRepository;
import com.aninfo.model.Ticket;
import com.aninfo.model.Severity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String name, String info, Severity severity, String creator, LocalDate startDate, LocalDate estimatedFinishDate) {
        ticketRepository.findTicketByName(name).ifPresent(x -> {throw new TicketNameAlreadyTakenException("Name already taken");});
        Ticket ticket = new Ticket(name, info, severity, creator, startDate, estimatedFinishDate);
        return ticketRepository.save(ticket);
    }

    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findByCode(Long code) {
        return ticketRepository.findById(code).orElseThrow(() -> new InvalidTicketException("No ticket found with that code"));
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteByCode(Long code) {
        ticketRepository.deleteById(code);
    }

    public Collection<Ticket> findAll() {
        return ticketRepository.findAll();
    }

}
