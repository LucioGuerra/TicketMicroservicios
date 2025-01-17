package com.ticket.comment_sv.controller;

import com.ticket.comment_sv.anotation.MaxFileListSize;
import com.ticket.comment_sv.dto.CommentDTO;
import com.ticket.comment_sv.dto.GetCommentDTO;
import com.ticket.comment_sv.dto.UpdateCommentDTO;
import com.ticket.comment_sv.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/public/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestPart("comment") CommentDTO commentDTO,
                                              @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        return commentService.createComment(commentDTO, files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCommentDTO> getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/requirement/{requirementId}")
    public ResponseEntity<Page<GetCommentDTO>> getAllCommentsForRequirement(@PathVariable Long requirementId,
                                                                            @PageableDefault(sort = "createdAt",
                                                                                    direction = Sort.Direction.DESC) @MaxFileListSize Pageable pageable) {
        return commentService.getAllCommentsForRequirement(requirementId, pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable Long id, @RequestBody UpdateCommentDTO commentDTO) {
        return commentService.updateComment(id, commentDTO);
    }

    @DeleteMapping("/{id}/files")
    public ResponseEntity<Void> deleteFiles(@PathVariable Long id, @RequestParam("fileName") List<String> fileNames) {
        return commentService.deleteFile(id, fileNames);
    }

    @PostMapping("/{id}/files")
    public ResponseEntity<Void> uploadFiles(@PathVariable Long id,
                                            @RequestPart("files") @MaxFileListSize List<MultipartFile> files) {
        return commentService.uploadFiles(id, files);
    }
}
