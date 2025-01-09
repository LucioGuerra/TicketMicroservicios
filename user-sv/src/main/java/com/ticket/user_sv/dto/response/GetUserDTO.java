package com.ticket.user_sv.dto.response;

import lombok.Data;

@Data
public class GetUserDTO {

    private Long id;
    private String name;
    private String email;
    private String username;

    private String position;
    private String department;
    private String userFile;

    private Boolean sla;
    private String cuil;
    private String description;
    private String company; 
}
