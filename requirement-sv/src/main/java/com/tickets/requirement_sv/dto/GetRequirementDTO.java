package com.tickets.requirement_sv.dto;

import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.State;
import com.tickets.requirement_sv.external.model.Category;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.external.model.User;
import lombok.Data;

import java.util.HashSet;

@Data
public class GetRequirementDTO {
    private Long id;
    private String subject;
    private String code;
    private String description;
    private State state;
    private Priority priority;
    private User creator;
    private User assignee;
    private Category category;
    private Type type;
    private HashSet<GetRequirementDTO> requirements;
}
