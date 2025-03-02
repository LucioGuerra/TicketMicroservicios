package com.ticket.comment_sv.service;

import com.ticket.comment_sv.dto.CommentDTO;
import com.ticket.comment_sv.dto.GetCommentDTO;
import com.ticket.comment_sv.dto.UpdateCommentDTO;
import com.ticket.comment_sv.entity.Comment;
import com.ticket.comment_sv.exception.TicketException;
import com.ticket.comment_sv.external.model.Requirement;
import com.ticket.comment_sv.external.model.User;
import com.ticket.comment_sv.repository.CommentRepository;
import com.ticket.comment_sv.repository.OutsideUserRepository;
import com.ticket.comment_sv.repository.RequirementRepository;
import com.ticket.shared.service.FileService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;
    private final RequirementRepository commentService;
    private final OutsideUserRepository outsideUserRepository;

    public ResponseEntity<GetCommentDTO> createComment(CommentDTO commentDTO, List<MultipartFile> files){
        Optional<User> user = outsideUserRepository.getUserById(commentDTO.getUserId());
        if (user.isEmpty()) {
            throw new TicketException("USER_NOT_EXIST", "User not found with id: " + commentDTO.getUserId());
        }

        if (commentService.validateRequirementById(commentDTO.getRequirementId())) {
            throw new TicketException("REQUIREMENT_NOT_FOUND", "Requirement not found");
        }

        Comment comment = modelMapper.map(commentDTO, Comment.class);

        List<String> sanitizedFiles = fileService.uploadFiles(files);
        comment.setFiles(sanitizedFiles);

        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED)
                
                .body(modelMapper.map(comment, GetCommentDTO.class));
    }

    public ResponseEntity<GetCommentDTO> getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new TicketException("COMMENT_NOT_FOUND", "Comment not found with id: " + id));

        Optional<User> user = outsideUserRepository.getUserById(comment.getUserId());
        if (user.isEmpty()) {
            throw new TicketException("USER_NOT_EXIST", "User not found with id: " + comment.getUserId());
        }

        GetCommentDTO commentDTO = modelMapper.map(comment, GetCommentDTO.class);

        if (!comment.getFiles().isEmpty()){
            commentDTO.setFiles(comment.getFiles());
        }

        return ResponseEntity.ok(commentDTO);
    }

    public ResponseEntity<Page<GetCommentDTO>> getAllCommentsForRequirement(Long requirementId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByRequirementIdAndDeletedIsFalse(requirementId, pageable);

        Set<Long> userIds = comments.stream()
                .map(Comment::getUserId)
                .collect(Collectors.toSet());

        List<User> users = outsideUserRepository.getUsersByIds(userIds);

        Map<Long, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        Page<GetCommentDTO> commentsDTO = comments.map(comment -> modelMapper.map(comment, GetCommentDTO.class));

        for (GetCommentDTO commentDTO : commentsDTO) {
            commentDTO.setUser(userMap.get(commentDTO.getUser().getId()));
        }

        return ResponseEntity.status(HttpStatus.OK)
                
                .body(commentsDTO);
    }

    public ResponseEntity<Void> deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new TicketException("COMMENT_NOT_FOUND", "Comment not found"));

        comment.setDeleted(true);
        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                
                .build();
    }

    public ResponseEntity<Void> updateComment(Long id, UpdateCommentDTO updateCommentDTO){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new TicketException("COMMENT_NOT_FOUND", "Comment not found"));

        if (updateCommentDTO.getSubject() != null) {
            comment.setSubject(updateCommentDTO.getSubject());
        }

        if (updateCommentDTO.getDescription() != null) {
            comment.setDescription(updateCommentDTO.getDescription());
        }

        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                
                .build();
    }

    public ResponseEntity<Void> deleteFile(Long id, List<String> fileNames) {
        Comment comment = commentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new TicketException("COMMENT_NOT_FOUND", "Comment not found"));

        if (comment.getFiles().isEmpty()) {
            throw new TicketException("REQUIREMENT_HAS_NO_FILES", "The requirement has no files");
        }

        for (String fileName : fileNames) {
            if (!comment.getFiles().contains(fileName)) {
                throw new TicketException("FILE_NOT_FOUND", "The file was not found in the requirement");
            }
            fileService.deleteFile(fileName);
            comment.getFiles().remove(fileName);
        }

        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                
                .build();
    }

    public ResponseEntity<Void> uploadFiles(Long id, List<MultipartFile> files) {
        Comment comment = commentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new TicketException("COMMENT_NOT_FOUND", "Comment not found"));

        if(comment.getFiles().size() + files.size() > 5){
            throw new TicketException("MAX_FILES_EXCEEDED", "The maximum number of files is 5");

        }

        List<String> sanitizedFiles = fileService.uploadFiles(files);
        comment.getFiles().addAll(sanitizedFiles);

        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                
                .build();
    }

    public ResponseEntity<byte[]> downloadFile(String fileName) {
        byte[] file = fileService.downloadFile(fileName);
        String contentType = fileService.getContentType(fileName);

        if (contentType == null || contentType.isBlank()) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(file);
    }
}
