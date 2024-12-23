package com.tickets.type_sv.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {
    @NotBlank(message = "Description is required")
    @Size(max = 49, message = "Description must be a maximum of 50 characters")
    private String description;

    @NotNull(message = "Type ID is required")
    private Long typeId;
}
