package com.tickets.requirement_sv.dto;

import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.State;
import com.tickets.requirement_sv.external.model.Category;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.external.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

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
    private HashSet<GetRequirementDTO> requirements; //todo: revisar si debe ser este dto o uno mas acotado para ser
                                                        // mas escalable tambien que get all requiremets
    private List<String> files;
    private LocalDate createdAt;
    private LocalTime createdTime;
}
