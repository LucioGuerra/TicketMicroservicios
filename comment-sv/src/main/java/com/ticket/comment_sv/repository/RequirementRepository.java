package com.ticket.comment_sv.repository;

import com.ticket.comment_sv.external.model.Requirement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "requirement-sv", url = "localhost:8083/public/requirements")
public interface RequirementRepository {
    @GetMapping("/{id}")
    Requirement getRequirementById(@PathVariable Long id);

    @GetMapping("/{id}/validate")
    Boolean validateRequirementById(@PathVariable Long id);
}
