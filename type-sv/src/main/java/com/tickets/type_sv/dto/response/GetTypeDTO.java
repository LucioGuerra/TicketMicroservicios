package com.tickets.type_sv.dto.response;

import lombok.Data;

@Data
public class GetTypeDTO {
    private Long id;
    private String code;
    private String description;
}
