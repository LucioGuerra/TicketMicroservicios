package com.ticket.comment_sv.controller;

import com.ticket.comment_sv.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/public/comment")
public class CommentController {

    private final CommentService commentService;
}
