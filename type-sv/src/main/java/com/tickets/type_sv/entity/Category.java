package com.tickets.type_sv.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.tickets.type_sv.entity.Type;

import java.time.LocalDateTime;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Type type;

    @Column(nullable = false)
    private Boolean deleted;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    private Category() {
        this.deleted = false;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
