package com.ticket.comment_sv.service;

import com.ticket.comment_sv.repository.CommentRepository;
import com.ticket.shared.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FileService fileService;
}
