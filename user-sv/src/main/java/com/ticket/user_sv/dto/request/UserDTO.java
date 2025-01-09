package com.ticket.user_sv.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserDTO {

    @NotBlank(message = "El nombre no puede estar vacío.")
    private String name;

    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Email(message = "El correo electrónico debe tener un formato válido.")
    private String email;

    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    private String username;

    private String position;
    private String department;
    private String userFile;

    private Boolean sla;
    private String cuil;
    private String description;
    private String company;
}
