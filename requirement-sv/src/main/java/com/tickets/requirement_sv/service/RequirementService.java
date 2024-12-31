package com.tickets.requirement_sv.service;

import com.tickets.requirement_sv.dto.GetRequirementDTO;
import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.dto.UpdateRequirementDTO;
import com.tickets.requirement_sv.entity.Requirement;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.repository.RequirementRepository;
import com.tickets.requirement_sv.repository.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final TypeRepository typeRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<Void> createRequirement(RequirementDTO requirementDTO) {
        Requirement requirement = modelMapper.map(requirementDTO, Requirement.class);

        if(!requirementDTO.getRequirements().isEmpty()){
            requirement.setRequirements(requirementRepository.findAllByIdsAndNotDeleted(requirementDTO.getRequirements()));
        }

        Type type = typeRepository.getTypeById(requirementDTO.getTypeId().toString());

        requirementRepository.save(requirement);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<GetRequirementDTO> getRequirementDTOById(Long id) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);
        GetRequirementDTO getRequirementDTO = modelMapper.map(requirement, GetRequirementDTO.class);
        return ResponseEntity.ok(getRequirementDTO);
    }

    public ResponseEntity<List<GetRequirementDTO>> getAllRequirements() {
        List<Requirement> requirements = requirementRepository.findAllByIsDeletedFalse();
        List<GetRequirementDTO> getRequirementDTOS = requirements.stream()
                .map(requirement -> modelMapper.map(requirement, GetRequirementDTO.class))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(getRequirementDTOS);
    }

    public ResponseEntity<Void> updateRequirement(UpdateRequirementDTO updateRequirementDTO, Long id) {
        Requirement requirementToUpdate = this.getRequirementByIdAndNotDeleted(id);
        //todo: aplicar camvios de updateRequirementDTO a requirementToUpdate

        requirementRepository.save(requirementToUpdate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Void> deleteRequirement(Long id) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);
        requirement.setIsDeleted(true);
        requirementRepository.save(requirement);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Requirement getRequirementByIdAndNotDeleted(Long id) {
        return requirementRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException(
                "Requirement not found with id: " + id));
    }

    private String generateCode(String typeCode) {
        return  typeCode + "-" + LocalDate.now().getYear() + "-" + String.format("%010d",
                requirementRepository.count() + 1);
    }
}
