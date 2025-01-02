package com.tickets.requirement_sv.service;

import com.tickets.requirement_sv.dto.GetRequirementDTO;
import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.dto.UpdateRequirementDTO;
import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.Requirement;
import com.tickets.requirement_sv.entity.State;
import com.tickets.requirement_sv.exception.TicketException;
import com.tickets.requirement_sv.external.model.Category;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.repository.CategoryRepository;
import com.tickets.requirement_sv.repository.RequirementRepository;
import com.tickets.requirement_sv.repository.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final TypeRepository typeRepository;

    public ResponseEntity<Void> createRequirement(RequirementDTO requirementDTO) {
        Requirement requirement = modelMapper.map(requirementDTO, Requirement.class);

        if(!requirement.getRequirements().isEmpty()){
            requirement.setRequirements(requirementRepository.findAllByIdsAndNotDeleted(requirementDTO.getRequirements()));
        }

        Category category = categoryRepository.getCategoryById(requirementDTO.getCategoryId());

        if(category.getType().getId() != requirementDTO.getTypeId()){
            throw new TicketException("TYPE_NOT_MATCH_CATEGORY", "The type does not match the category");
        }
        //todo: Validar los id de user

        //todo: mandar evento por kafka para crear el requerimiento

        requirement.setCode(this.generateCode(category.getType().getCode()));


        requirementRepository.save(requirement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<GetRequirementDTO> getRequirementDTOById(Long id) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);

        System.err.println(requirement.toString());

        GetRequirementDTO requirementDTO = modelMapper.map(requirement, GetRequirementDTO.class);

        requirementDTO.setCategory(categoryRepository.getCategoryById(requirement.getCategoryId()));
        requirementDTO.setType(typeRepository.getTypeById(requirement.getTypeId()));
        //todo: setear usuario creador

        if(requirement.getAssigneeId() != null){
            //todo: setear el usuario asignado
        }

        if(!requirement.getRequirements().isEmpty()){
            //todo: setear los requerimientos relacionados
        }

        return ResponseEntity.status(HttpStatus.OK).body(requirementDTO);
    }

    public ResponseEntity<Page<GetRequirementDTO>> getAllRequirements(String subject, Long typeId, Long creatorId,
                                                                      Long assigneeId, State state, Priority priority,
                                                                      Pageable pageable) {

        Specification<Requirement> specification = Specification.where(n);


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
