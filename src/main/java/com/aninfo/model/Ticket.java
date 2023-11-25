package com.aninfo.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.time.LocalDate;

enum State{
    OPEN,
    IN_PROGRESS,
    PAUSED,
    COMPLETED
}

@Entity
public class Ticket {

    private String name;
    private String info;
    private State state;
    private Severity severity;
    private String creator;
    private LocalDate startDate;
    private LocalDate estimatedFinishDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long code;


    public Ticket() {
    }

    public Ticket(String name, String info, Severity severity, String creator, LocalDate startDate, LocalDate estimatedFinishDate) {
        this.name = name;
        this.info = info;
        this.state = State.OPEN;
        this.severity = severity;
        this.creator = creator;
        this.startDate = startDate;
        this.estimatedFinishDate = estimatedFinishDate;
    }
}