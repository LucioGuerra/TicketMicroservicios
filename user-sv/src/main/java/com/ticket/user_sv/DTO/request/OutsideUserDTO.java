package com.ticket.user_sv.DTO.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OutsideUserDTO {

    @NotBlank(message = "Email is mandatory")
    private String email;

    private String username;

    @NotBlank(message = "user must be not blank" )
    private String name;

    private String position;
    private String department;
    private Integer userFile;

    @NotNull(message = "sla must be not null")
    private Boolean sla;

    private String cuil;

    @Size(max = 500, message = "description must be less than 500 characters")
    private String description;

    private String company;

    private Boolean active;
}
