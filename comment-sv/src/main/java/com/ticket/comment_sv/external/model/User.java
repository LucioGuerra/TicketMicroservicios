package com.ticket.comment_sv.external.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private String lastName; //todo: ver si zoe lo puso asi
    private String image; //todo: ver como se va a gestionar la imagen
}
