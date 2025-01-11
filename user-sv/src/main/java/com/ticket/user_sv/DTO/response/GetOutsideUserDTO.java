package com.ticket.user_sv.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetOutsideUserDTO {

    //todo: preguntarle a lucio como administro el id en el dto porque no se lo puedo pedir al usuario.
    private Long id;
    private String name;
    private String email;
    private String username;
    private String position;
    private String department;
    private Integer userFile;
    private Boolean sla;
    private String cuil;
    private String company;
    private String description;
    private String status;

}
