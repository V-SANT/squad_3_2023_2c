package com.aninfo.model;


import java.time.LocalDate;

public class TaskResponse{

    private Long id;
    
    private Long projectId;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private Long estimatedDuration;
    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Long asignedId;

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

    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
    public void setStatus(Status status){ this.status = status; }
    public void setPriority(Priority priority){ this.priority = priority; }
    public void setEstimatedDuration(Long estimatedDuration){ this.estimatedDuration = estimatedDuration; }
    public void setFinishDate(LocalDate finishDate){ this.finishDate = finishDate; }

    public TaskResponse(){

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