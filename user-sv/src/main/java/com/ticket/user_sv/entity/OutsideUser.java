package com.ticket.user_sv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name= "outside_user")
public class OutsideUser {

    @Id
    private int id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String company;
    @Column(unique = true)
    private String cuil;
    //todo: preguntarle a Lucio como manejar los users y passwords (user unico y password plana?)
    @Column
    private String username;
    @Column
    private String password;
    @Column(length = 500)
    private String description;
    @Column
    private Boolean sla;
    @Column
    private String status;


    //todo: debería ser un constructor vacío?s
    public OutsideUser() {
    }

}
