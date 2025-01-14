package com.ticket.comment_sv.service;

import com.ticket.comment_sv.dto.CommentDTO;
import com.ticket.comment_sv.dto.GetCommentDTO;
import com.ticket.comment_sv.entity.Comment;
import com.ticket.comment_sv.exception.TicketException;
import com.ticket.comment_sv.external.model.Requirement;
import com.ticket.comment_sv.repository.CommentRepository;
import com.ticket.comment_sv.repository.RequirementRepository;
import com.ticket.shared.service.FileService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;
    private final RequirementRepository requirementRepository;

    public ResponseEntity<Void> createComment(CommentDTO commentDTO, List<MultipartFile> files){
        //todo: validar que existe el usuario


        if (requirementRepository.validateRequirementById(commentDTO.getRequirementId())) {
            throw new TicketException("REQUIREMENT_NOT_FOUND", "Requirement not found");
        }

        Comment comment = modelMapper.map(commentDTO, Comment.class);

        List<String> sanitizedFiles = fileService.uploadFiles(files);
        comment.setFiles(sanitizedFiles);

        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<GetCommentDTO> getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new TicketException("COMMENT_NOT_FOUND", "Comment not found"));

        //todo: recuperar los datos minimos del usuario
        //todo: recuperar los archivos si tiene

        return ResponseEntity.ok(modelMapper.map(comment, GetCommentDTO.class));
    }

    public ResponseEntity<Page<GetCommentDTO>> getAllCommentsForRequirement(Long requirementId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByRequirementIdAndDeletedIsFalse(requirementId, pageable);

        Page<GetCommentDTO> commentsDTO = comments.map(comment -> modelMapper.map(comment, GetCommentDTO.class));

        //todo: recuperar los datos minimos del usuario

        return ResponseEntity.status(HttpStatus.OK).body(commentsDTO);
    }


}
