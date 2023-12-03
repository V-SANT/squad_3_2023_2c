package com.aninfo.model;

public class TicketCreationRequest {
    private String title;
    private String description;
    private String status;
    private String severity;
    private String priority;
    private String product;
    private String version;
    private String clientId;
    private String employeeId;
    private String estimatedClosingDate;

    public TicketCreationRequest() {
    }

    public TicketCreationRequest(
        String title, 
        String description,
        String status,
        String severity,
        String priority,
        String product,
        String version,
        String clientId,
        String employeeId,
        String estimatedClosingDate){
        this.title = title;
        this.description = description;
        this.status = status;
        this.severity = severity;
        this.product = product;
        this.version = version;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.estimatedClosingDate = estimatedClosingDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() { 
        return status; 
    }

    public String getSeverity() { 
        return severity; 
    }

    public String getPriority() { 
        return priority; 
    }

    public String getProduct() { 
        return product; 
    }

    public String getVersion() { 
        return version; 
    }

    public String getClientId() { 
        return clientId; 
    }

    public String getEmployeeId() { 
        return employeeId; 
    }

    public String getEstimatedClosingDate() { 
        return estimatedClosingDate; 
    }

    public Status getMappedStatus(){
        if (this.status == "No comenzado"){
            return Status.NOT_STARTED;
        }
        if (this.status == "En proceso"){
            return Status.IN_PROGRESS;
        }
        if (this.status == "Bloqueado"){
            return Status.BLOCKED;
        }
        else return Status.COMPLETED;
    }


    public Severity getMappedSeverity(){
        if (this.severity == "S1")
            return Severity.S1;
        if (this.severity == "S2")
            return Severity.S2;
        if (this.severity == "S3")
            return Severity.S3;
        else return Severity.S4;
    }

    public Priority getMappedPriority(){
        if (this.priority == "Baja")
            return Priority.LOW;
        if (this.priority == "Media")
            return Priority.MEDIUM;
        else {
            return Priority.HIGH;
        }
    }
}

