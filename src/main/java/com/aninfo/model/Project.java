package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Status status;
    private LocalDate startDate;
    private LocalDate estimatedFinishDate;
    // project leader, when we know what it is

    public String getName(){ return name; }
    public Long getId(){ return id; }
    public String getDescription(){ return description; }
    public Status getStatus(){ return status; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getEstimatedFinishDate(){ return estimatedFinishDate; }

    public Project(String name, String description, LocalDate startDate, LocalDate estimatedFinishDate){
        this.name = name;
        this.status = Status.NOT_STARTED;
        this.description = description;
        this.startDate = startDate;
        this.estimatedFinishDate = estimatedFinishDate;
    }

    public Project() {

    }
}
