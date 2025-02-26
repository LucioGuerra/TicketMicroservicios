package com.ticket.comment_sv.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", nullable = false, length = 50)
    private String subject;

    @Column(name = "description", nullable = false, length = 5000)
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ElementCollection
    private List<String> files;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "requirement_id", nullable = false, updatable = false)
    private Long requirementId;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean deleted;

    private Comment(){
        this.deleted = false;
        this.files = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
