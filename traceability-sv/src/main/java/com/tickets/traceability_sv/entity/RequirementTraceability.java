package com.tickets.traceability_sv.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
