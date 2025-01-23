package com.ticket.user_sv.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "outside_user")
public class OutsideUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String company;
    @Column(unique = true)
    private String cuil;
    @Column
    private String username;
    @Column(length = 500)
    private String description;
    @Column
    private Boolean sla;
    @Column
    private Boolean active;

    public OutsideUser() {
        this.active = true;
    }

}
