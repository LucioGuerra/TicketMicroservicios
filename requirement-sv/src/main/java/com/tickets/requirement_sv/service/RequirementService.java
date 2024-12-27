package com.tickets.requirement_sv.service;

import com.tickets.requirement_sv.entity.Requirement;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.repository.RequirementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;

    public void createRequirement(Requirement requirement) {
        requirementRepository.save(requirement);
    }

    public Requirement getRequirementDTOById(Long id) {
        return requirementRepository.findById(id).orElse(null);
    }

    public void getAllRequirements() {
        requirementRepository.findAll();
    }

    public void updateRequirement(Requirement requirement) {
        requirementRepository.save(requirement);
    }

    public void deleteRequirement(Long id) {
        requirementRepository.deleteById(id);
    }

    private Requirement getRequirementById(Long id) {
        return requirementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Requirement not found " +
                "with id: " + id));
    }

    private String generateCode(String typeCode) {
        return  typeCode + "-" + LocalDate.now().getYear() + "-" + String.format("%010d",
                requirementRepository.count() + 1);
    }
}
