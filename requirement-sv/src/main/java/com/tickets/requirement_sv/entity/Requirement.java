package com.tickets.requirement_sv.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String subject;

    @Column(nullable = false, unique = true, length = 19)
    private String code;

    @Column(length = 5000, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @ElementCollection
    private List<String> files;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "assignee_id")
    private Long assigneeId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Requirement> requirements;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    private Requirement() {
        this.state = State.OPEN;
        this.isDeleted = false;
        this.requirements = new HashSet<>();
    }


    @PrePersist
    private void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
