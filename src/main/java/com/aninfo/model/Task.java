package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numTask;

    private Long projectId;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Long estimatedDuration;


    public Long getNumTask(){ return numTask; }
    public String getName(){ return name; }
    public Long getProjectId() { return projectId;}
    public String getDescription(){ return description; }
    public Status getStatus(){ return status; }
    public Priority getPriority(){ return priority; }
    public Long getEstimatedDuration(){ return estimatedDuration; }


    public Task() {

    }

    public Task(Long projectId, String name, String description, Priority priority, Long estimatedDuration) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.status = Status.NOT_STARTED;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
    }

}
