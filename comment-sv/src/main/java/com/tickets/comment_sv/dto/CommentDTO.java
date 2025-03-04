package com.tickets.comment_sv.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    @NotBlank(message = "Subject is required")
    @Size(min = 3, max = 50, message = "Subject must be between 3 and 50 characters")
    private String subject;

    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 5000, message = "Description must be between 3 and 5000 characters")
    private String description;

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Requirement id is required")
    private Long requirementId;
}
