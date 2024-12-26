package com.tickets.traceability_sv.event;

import com.tickets.traceability_sv.entity.Action;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RequirementTracebilityEvent {
    private Action action;
    private String code;
    private Long userId;
    private String email;
    private LocalDateTime dateTime;
}
