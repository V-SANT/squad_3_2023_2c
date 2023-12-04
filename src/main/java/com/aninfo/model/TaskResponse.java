package com.aninfo.model;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public class TaskResponse{

    private final Long id;
    
    private final Long projectId;
    private final String name;
    private final String description;
    private final Status status;
    private final Priority priority;
    private final Long estimatedDuration;
    private final LocalDate creationDate;
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final Long asignedId;

    public Long getId(){ return id; }
    public String getName(){ return name; }
    public Long getProjectId() { return projectId;}
    public String getDescription(){ return description; }
    public Status getStatus(){ return status; }
    public Priority getPriority(){ return priority; }
    public Long getEstimatedDuration(){ return estimatedDuration; }
    public LocalDate getCreationDate(){ return creationDate; }
    public LocalDate getStartDate(){ return startDate; }
    public LocalDate getFinishDate(){ return finishDate; }
    public Long getAsignedId(){
        return asignedId;
    }

    public TaskResponse(Long id, Long projectId, String name, String description, Priority priority, Long estimatedDuration, LocalDate startDate, LocalDate finishDate, Long asignedId){
        this.id = id;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.status = Status.NOT_STARTED;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
        this.creationDate = LocalDate.now();
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.asignedId = asignedId;
    }


}