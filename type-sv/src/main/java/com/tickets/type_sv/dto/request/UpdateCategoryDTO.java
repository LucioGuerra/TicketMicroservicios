package com.tickets.type_sv.dto.request;

import lombok.Data;

@Data
public class UpdateCategoryDTO {
    private String description;
    private Long typeId;
}
