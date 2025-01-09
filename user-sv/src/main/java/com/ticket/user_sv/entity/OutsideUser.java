package com.ticket.user_sv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "outside_user")

public class OutsideUser {

    private String name;
    private String email;
    private Integer userFile;
    private String username;
    private String password;
    private String position;
    private String department;
    private String company;
    private Boolean sla;

    //@Column(unique = true)
    private String cuil;

    //@Column(length = 500)
    private String description;

    private OutsideUser(){
    }

    public OutsideUser(String email, String username, String name, String position, String department, Integer userFile, String cuil, String description, String company, Boolean sla) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.position = position;
        this.department = department;
        this.userFile = userFile;
        this.cuil = cuil;
        this.description = description;
        this.company = company;
        this.sla = sla;

    }
}

