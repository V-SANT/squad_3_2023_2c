package com.aninfo.service;

import com.aninfo.exceptions.TicketTitleAlreadyTakenException;
import com.aninfo.exceptions.InvalidTicketException;
import com.aninfo.repository.TicketRepository;
import com.aninfo.model.Ticket;
import com.aninfo.model.TicketCreationRequest;
import com.aninfo.model.Severity;
import com.aninfo.model.Status;
import com.aninfo.model.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static java.util.Objects.nonNull;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(TicketCreationRequest ticketRequest) {

        String title = ticketRequest.getTitle();
        ticketRepository.findTicketByTitle(title).ifPresent(x -> {throw new TicketTitleAlreadyTakenException("Title already taken");});

        String description = ticketRequest.getDescription();
        Status status = ticketRequest.getMappedStatus();
        Severity severity = ticketRequest.getMappedSeverity();
        Priority priority = ticketRequest.getMappedPriority();
        String product = ticketRequest.getProduct();
        String version = ticketRequest.getVersion();
        Long clientId = Long.parseLong(ticketRequest.getClientId());
        Long employeeId = Long.parseLong(ticketRequest.getEmployeeId());
        LocalDate estimatedClosingDate = LocalDate.parse(ticketRequest.getEstimatedClosingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        Ticket ticket = new Ticket(title, description, status, severity, priority, product, version, clientId, employeeId, estimatedClosingDate);
        return ticketRepository.save(ticket);
    }

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

    public Ticket updateTicket(Long code,
        String title,
        String description,
        Status status,
        Severity severity,
        Priority priority,
        String product,
        String version,
        Long clientId,
        Long employeeId,
        List<Long> tasksIds,
        LocalDate date
        ){
        Ticket ticket = ticketRepository.findById(code).orElseThrow(() -> new InvalidTicketException("No ticket found with that code"));
        
        if (nonNull(title)){
            ticketRepository.findTicketByTitle(title).ifPresent(x -> {
                if (!x.getCode().equals(code)) {
                    throw new TicketTitleAlreadyTakenException("Title already taken");
                }
            });
        }

        ticket.setTitle(nonNull(title) ? title : ticket.getTitle());
        ticket.setDescription(nonNull(description) ? description : ticket.getDescription());
        ticket.setStatus(nonNull(status) ? status : ticket.getStatus());
        ticket.setSeverity(nonNull(severity) ? severity : ticket.getSeverity());
        ticket.setPriority(nonNull(priority) ? priority : ticket.getPriority());
        ticket.setProduct(nonNull(product) ? product : ticket.getProduct());
        ticket.setVersion(nonNull(version) ? version : ticket.getVersion());
        ticket.setClientId(nonNull(clientId) ? clientId : ticket.getClientId());
        ticket.setAssignatedEmployeeId(nonNull(employeeId) ? employeeId : ticket.getAssignatedEmployeeId());
        ticket.setAssociatedTasks(nonNull(tasksIds) ? tasksIds : ticket.getAssociatedTasks());
        ticket.setClosingDate(nonNull(date) ? date : ticket.getClosingDate());

        return ticketRepository.save(ticket);
    }

    public Collection<Long> getTicketsAssociatedTask(Long taskId){
        Collection<Ticket> tickets = findAll();
        Collection<Long> associatedTickets = new ArrayList<Long>();
        for (Ticket ticket: tickets){
            for (Long associatedTaskId: ticket.getAssociatedTasks()){
                if (associatedTaskId == taskId){
                    associatedTickets.add(ticket.getCode());
                }
            }
        }
        return associatedTickets;
    }

    public void deleteAll(){ ticketRepository.deleteAll(); }
    
}
