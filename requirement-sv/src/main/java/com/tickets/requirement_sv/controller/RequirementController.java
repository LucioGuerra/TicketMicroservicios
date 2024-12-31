package com.tickets.requirement_sv.controller;

import com.tickets.requirement_sv.dto.GetRequirementDTO;
import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.dto.UpdateRequirementDTO;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.service.RequirementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<GetRequirementDTO>> getAllRequirements() {
        return requirementService.getAllRequirements();
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
