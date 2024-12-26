package com.tickets.traceability_sv.repository;

import com.tickets.traceability_sv.entity.RequirementTraceability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementTraceabilityRepository extends JpaRepository<RequirementTraceability, Long> {

    List<RequirementTraceability> findByCode(String code);
}
