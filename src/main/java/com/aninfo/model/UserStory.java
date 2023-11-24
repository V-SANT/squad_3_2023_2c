package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserStory {
    public String as;
    public String iWant;
    public String soThat;
    @Id
    private Long id;

    public UserStory(String as, String iWant, String soThat)
    {
        this.as = as;
        this.iWant = iWant;
        this.soThat = soThat;
    }

    public UserStory() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

