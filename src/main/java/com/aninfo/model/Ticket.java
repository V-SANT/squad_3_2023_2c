package com.aninfo.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    private String title;
    private String description;
    private Status status;
    private Severity severity;
    private Priority priority;
    private String product;
    private String version;
    private Long employeeId;
    private Long clientId;
    // @Column(columnDefinition = "longs[]")
    @ElementCollection
    @CollectionTable(name = "ticket_tasks", joinColumns = @JoinColumn(name = "ticket_id"))
    @Column(name = "task_id")
    private List<Long> associatedTasks;
    private LocalDate startDate;
    private LocalDate closingDate;

    
    public Ticket() {
    }
    
    public Ticket(
        String title,
        String description,
        Status status,
        Severity severity,
        Priority priority,
        String product,
        String version,
        Long clientId,
        Long employeeId,
        LocalDate closingDate
        ) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.severity = severity;
        this.priority = priority;
        this.product = product;
        this.version = version;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.associatedTasks = new ArrayList<>();
        this.startDate = LocalDate.now();
        this.closingDate = closingDate;
    }

    public Long getCode(){ return code;}
    public String getTitle(){ return title;}
    public String getDescription(){ return description;}
    public Status getStatus(){ return status;}
    public Severity getSeverity(){ return severity;}
    public Priority getPriority(){ return priority;}
    public String getProduct(){ return product;}
    public String getVersion(){ return version;}
    public Long getAssignatedEmployeeId(){ return employeeId;}
    public Long getClientId(){ return clientId;}
    public List<Long> getAssociatedTasks(){ return associatedTasks;}
    public LocalDate getStartDate(){ return startDate;}
    public LocalDate getClosingDate(){ return closingDate;}

    public void setCode(Long code){ this.code = code;}
    public void setTitle(String title){ this.title = title;}
    public void setDescription(String description){ this.description = description;}
    public void setStatus(Status status){ this.status = status;}
    public void setSeverity(Severity severity){ this.severity = severity;}
    public void setPriority(Priority priority){ this.priority = priority;}
    public void setProduct(String product){ this.product = product;}
    public void setVersion(String version){ this.version = version;}
    public void setEmployeeId(Long employeeId){ this.employeeId = employeeId;}
    public void setClientId(Long clientId){ this.clientId = clientId;}
    public void setAssociatedTasks(List<Long> associatedTasks){ this.associatedTasks = associatedTasks;}
    public void setClosingDate(LocalDate date){ this.closingDate = date;}
}