package com.tickets.requirement_sv.repository;

import com.tickets.requirement_sv.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    Optional<Requirement> findByIdAndIsDeletedFalse(Long id);

    List<Requirement> findAllByIsDeletedFalse();

    @Query("SELECT r FROM Requirement r WHERE r.id IN :ids AND r.isDeleted = false")
    HashSet<Requirement> findAllByIdsAndNotDeleted(@Param("ids") HashSet<Long> ids);
}
