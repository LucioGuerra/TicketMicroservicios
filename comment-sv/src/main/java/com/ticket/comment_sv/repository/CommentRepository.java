package com.ticket.comment_sv.repository;

import com.ticket.comment_sv.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByRequirementIdAndDeletedIsFalse(Long requirementId, Pageable pageable);
}
