package com.aninfo.model;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

public class TaskCreationRequest {
    private String name;
    private String description;
    private String priority;
    private String estimatedDuration;
    
    private String startDate;
    private String estimatedFinishDate;
    private String leaderId;
    
    public TaskCreationRequest() {
    }

    public TaskCreationRequest(String name, String description, String priority, String estimatedDuration, String startDate, String estimatedFinishDate, String leaderId){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
        this.startDate = startDate;
        this.estimatedFinishDate = estimatedFinishDate;
        this.leaderId = leaderId;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority()
    {
        return priority;
    }
    public String getEstimatedDuration(){return estimatedDuration;}

    public String getStartDate() {
        return startDate;
    }

    public String getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    public String getLeaderId() {return leaderId;}

}