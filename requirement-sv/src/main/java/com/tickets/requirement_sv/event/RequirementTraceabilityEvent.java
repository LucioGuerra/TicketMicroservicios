package com.tickets.requirement_sv.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequirementTraceabilityEvent {
    private Action action;
    private String code;
    private Long userId;
    private String email;
    private LocalDateTime dateTime;

    public RequirementTraceabilityEvent(Action action, String code, Long creatorId, String email) {
        this.action = action;
        this.code = code;
        this.userId = creatorId;
        this.email = email;
        this.dateTime = LocalDateTime.now();
    }
}
