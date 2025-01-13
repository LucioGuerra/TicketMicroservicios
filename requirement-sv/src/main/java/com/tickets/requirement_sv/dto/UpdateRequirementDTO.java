package com.tickets.requirement_sv.dto;

import com.tickets.requirement_sv.entity.Priority;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UpdateRequirementDTO {
    private String subject;
    private String description;
    private Priority priority;
    private HashSet<Long> requirements;
}
