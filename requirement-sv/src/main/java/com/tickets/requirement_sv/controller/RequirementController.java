package com.tickets.requirement_sv.controller;

import com.tickets.requirement_sv.dto.GetRequirementDTO;
import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.dto.UpdateRequirementDTO;
import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.State;
import com.tickets.requirement_sv.service.RequirementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/public/requirements")
public class RequirementController {

    private final RequirementService requirementService;

   @PostMapping
    public ResponseEntity<Void> createRequirement(@RequestBody @Valid RequirementDTO requirementDTO) {
        return requirementService.createRequirement(requirementDTO);
    }

    @GetMapping
    public ResponseEntity<Page<GetRequirementDTO>> getAllRequirements(
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false) Long creatorId,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) State state,
            @RequestParam(required = false) Priority priority,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return requirementService.getAllRequirements(subject, typeId, creatorId, assigneeId, state, priority, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRequirementDTO> getRequirementById(@PathVariable Long id) {
        return requirementService.getRequirementDTOById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateRequirement(@RequestBody @Valid UpdateRequirementDTO requirementDTO,
                                                  @PathVariable Long id) {
        return requirementService.updateRequirement(requirementDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement(@PathVariable Long id) {
       return requirementService.deleteRequirement(id);
    }
}
