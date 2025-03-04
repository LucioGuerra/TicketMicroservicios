package com.tickets.comment_sv.dto;

import com.tickets.comment_sv.external.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetCommentDTO {
    private Long id;
    private String subject;
    private String description;
    private LocalDateTime createdAt;
    private List<String> files;
    private User user;
}

