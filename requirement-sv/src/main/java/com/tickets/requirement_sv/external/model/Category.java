package com.tickets.requirement_sv.external.model;

import lombok.Data;

@Data
public class Category {
    private Long id;
    private String description;
    private Type type;
}
