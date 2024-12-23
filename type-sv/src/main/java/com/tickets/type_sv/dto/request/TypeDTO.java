package com.tickets.type_sv.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TypeDTO {
    @NotBlank(message = "Code is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Code must have 3 uppercase letters")
    private String code;

    @NotBlank(message = "Description is required")
    @Size(max = 50, message = "Description must have a maximum of 50 characters")
    private String description;
}
