package com.tickets.requirement_sv.dto;

import com.tickets.requirement_sv.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.List;

@Data
public class RequirementDTO {
    @Size(min = 1, max = 50, message = "Subject must be between 1 and 50 characters")
    @NotBlank(message = "Subject must not be blank")
    private String subject;

    @Size(min = 19, max = 19, message = "Code must be 19 characters")
    @NotBlank(message = "Code must not be blank")
    private String code;

    @Size(min = 1, max = 5000, message = "Description must be between 1 and 5000 characters")
    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Creator must not be null")
    private Long creatorId;

    @NotNull(message = "Assignee must not be null")
    private Long assigneeId;

    @NotNull(message = "Category must not be null")
    private Long categoryId;

    @NotNull(message = "Type must not be null")
    private Long typeId;

    @NotNull(message = "Priority must not be null")
    @NotBlank(message = "Priority must not be blank")
    private Priority priority;

    private HashSet<Long> requirements;
}