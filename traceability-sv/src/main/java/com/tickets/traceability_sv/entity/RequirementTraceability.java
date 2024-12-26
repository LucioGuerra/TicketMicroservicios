package com.tickets.traceability_sv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequirementTraceability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requirement_code", nullable = false, length = 19)
    @Pattern(regexp = "^[A-Z]{3}-[0-9]{4}-[0-9]{10}$")
    private String code;

    @Column(name = "date_and_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String Email;

    @Column(nullable = false)
    private Action action;
}
