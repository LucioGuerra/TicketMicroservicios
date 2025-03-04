package com.tickets.comment_sv.repository;

import com.tickets.comment_sv.external.model.Requirement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "requirement-sv")
public interface RequirementRepository {
    @GetMapping("/api/requirements/{id}")
    Requirement getRequirementById(@PathVariable Long id);

    @GetMapping("/api/requirements/{id}/validate")
    Boolean validateRequirementById(@PathVariable Long id);
}
