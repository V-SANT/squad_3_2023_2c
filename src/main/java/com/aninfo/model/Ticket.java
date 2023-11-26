package com.aninfo.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.time.LocalDate;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;

    private String name;
    private String info;
    private Status status;
    private Severity severity;
    private String creator;
    private LocalDate startDate;
    private LocalDate estimatedFinishDate;


    public Long getCode(){ return code;}
    public void setCode(Long code){ this.code = code;}
    public String getName(){ return name;}
    public String getInfo(){ return info;}
    public Status getStatus(){ return status;}
    public Severity getSeverity(){ return severity;}
    public String getCreator(){ return creator;}
    public LocalDate getStartDate(){ return startDate;}
    public LocalDate getEstimatedFinishDate(){ return estimatedFinishDate;}

    public Ticket() {
    }

    public Ticket(String name, String info, Severity severity, String creator, LocalDate startDate, LocalDate estimatedFinishDate) {
        this.name = name;
        this.info = info;
        this.status = Status.NOT_STARTED;
        this.severity = severity;
        this.creator = creator;
        this.startDate = startDate;
        this.estimatedFinishDate = estimatedFinishDate;
    }
}