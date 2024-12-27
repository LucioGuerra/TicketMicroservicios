package com.tickets.requirement_sv.controller;

import com.tickets.requirement_sv.service.RequirementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/requirement")
public class RequirementController {

    private final RequirementService requirementService;

    @PostMapping
    public ResponseEntity<Void> createRequirement() {
        return requirementService.createRequirement();
    }

    @GetMapping
    public ResponseEntity<Void> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getRequirementById() {
        return requirementService.getRequirementDTOById();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateRequirement() {
        return requirementService.updateRequirement();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement() {
        return requirementService.deleteRequirement();
    }
}
