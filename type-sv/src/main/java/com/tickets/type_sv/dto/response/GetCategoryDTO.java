package com.tickets.type_sv.dto.response;

import lombok.Data;

@Data
public class GetCategoryDTO {
    private Long id;
    private String description;
    private GetTypeDTO type;
}
