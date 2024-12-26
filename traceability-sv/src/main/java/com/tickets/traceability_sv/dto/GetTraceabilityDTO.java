package com.tickets.traceability_sv.dto;

import com.tickets.traceability_sv.entity.Action;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetTraceabilityDTO {
    private String code;
    private Action action;
    private String email;
    private Long userId;
    private LocalDateTime dateTime;
}
