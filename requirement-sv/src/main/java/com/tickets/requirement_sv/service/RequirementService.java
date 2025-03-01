package com.tickets.requirement_sv.service;

import com.ticket.shared.service.FileService;
import com.tickets.requirement_sv.dto.GetRequirementDTO;
import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.dto.UpdateRequirementDTO;
import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.Requirement;
import com.tickets.requirement_sv.entity.State;
import com.tickets.requirement_sv.event.Action;
import com.tickets.requirement_sv.event.RequirementTraceabilityEvent;
import com.tickets.requirement_sv.exception.TicketException;
import com.tickets.requirement_sv.external.model.Category;
import com.tickets.requirement_sv.external.model.Type;
import com.tickets.requirement_sv.external.model.User;
import com.tickets.requirement_sv.repository.CategoryRepository;
import com.tickets.requirement_sv.repository.OutsideUserRepository;
import com.tickets.requirement_sv.repository.RequirementRepository;
import com.tickets.requirement_sv.repository.TypeRepository;
import com.tickets.requirement_sv.specification.RequirementSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final TypeRepository typeRepository;
    private final OutsideUserRepository outsideUserRepository;
    private final KafkaTemplate<String, RequirementTraceabilityEvent> kafkaTemplate;
    private final FileService fileService;


    public ResponseEntity<GetRequirementDTO> createRequirement(RequirementDTO requirementDTO, List<MultipartFile> files) {
        Requirement requirement = modelMapper.map(requirementDTO, Requirement.class);

        if(!requirement.getRequirements().isEmpty()){
            requirement.setRequirements(requirementRepository.findAllByIdsAndNotDeleted(requirementDTO.getRequirements()));
        }

        Category category = categoryRepository.getCategoryById(requirementDTO.getCategoryId());

        if(!category.getType().getId().equals(requirementDTO.getTypeId())){
            throw new TicketException("TYPE_NOT_MATCH_CATEGORY", "The type does not match the category");
        }
        Optional<User> user = outsideUserRepository.getOutsideUserById(requirement.getCreatorId());
        if(user.isEmpty()){
            throw new TicketException("USER_NOT_EXIST", "user does not exist");
        }

        requirement.setCode(this.generateCode(category.getType().getCode()));

        List<String> sanitizedFiles = fileService.uploadFiles(files);
        requirement.setFiles(sanitizedFiles);

        this.sendRequirementTraceabilityEvent(Action.CREATE, requirement.getCode(), requirement.getCreatorId(), user.get().getEmail());
        requirementRepository.save(requirement);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Access-Control-Allow-Origin", "*")
                .body(modelMapper.map(requirement, GetRequirementDTO.class));
    }

    private void sendRequirementTraceabilityEvent(Action action, String code, Long userId, String userEmail) {
        RequirementTraceabilityEvent event = new RequirementTraceabilityEvent(action, code, userId, userEmail);
        kafkaTemplate.send("requirement-traceability", event);
    }

    public ResponseEntity<GetRequirementDTO> getRequirementDTOById(Long id) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);

        System.err.println(requirement.toString());

        GetRequirementDTO requirementDTO = modelMapper.map(requirement, GetRequirementDTO.class);

        requirementDTO.setCategory(categoryRepository.getCategoryById(requirement.getCategoryId()));
        requirementDTO.setType(typeRepository.getTypeById(requirement.getTypeId()));

        Optional<User> creator = outsideUserRepository.getOutsideUserById(requirement.getCreatorId());
        if(creator.isEmpty()){
            throw new TicketException("CREATOR_NOT_EXIST", "creator user does not exist");
        }
        requirementDTO.setCreator(creator.get());

        if(requirement.getAssigneeId() != null){
            //todo: setear el usuario asignado
            //Optional<User> assignee = outsideUserRepository.getOutsideUserById(requirement.getAssigneeId());
            User assignee = new User();
            assignee.setId(1L);
            assignee.setUsername("Usuario");
            assignee.setName("usuario interno mockeado");
            assignee.setEmail("mock@mock.com");
        }

        if(!requirement.getRequirements().isEmpty()){
            HashSet<GetRequirementDTO> requirements = new HashSet<>();
            for(Requirement req : requirement.getRequirements()){
                requirements.add(modelMapper.map(req, GetRequirementDTO.class));
            }
            requirementDTO.setRequirements(requirements);
        }

        if (!requirement.getFiles().isEmpty()) {
            requirementDTO.setFiles(requirement.getFiles());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .header("Access-Control-Allow-Origin", "*")
                .body(requirementDTO);
    }

    public ResponseEntity<Page<GetRequirementDTO>> getAllRequirements(String subject, Long typeId, Long creatorId,
                                                                      Long assigneeId, State state, Priority priority,
                                                                      Pageable pageable) {

        Specification<Requirement> specification = Specification.where(RequirementSpecification.hasDeleted(false))
                .and(RequirementSpecification.hasSubject(subject))
                .and(RequirementSpecification.hasType(typeId))
                .and(RequirementSpecification.hasCreatorId(creatorId))
                .and(RequirementSpecification.hasAssigneeId(assigneeId))
                .and(RequirementSpecification.hasState(state))
                .and(RequirementSpecification.hasPriority(priority));


        Page<Requirement> requirements = requirementRepository.findAll(specification, pageable);

        if (requirements.isEmpty()) {
            throw new EntityNotFoundException("No requirements found with the given filters");
        }

        Page<GetRequirementDTO> requirementDTOs = requirements.map(requirement -> modelMapper.map(requirement,
                GetRequirementDTO.class));


        return ResponseEntity.status(HttpStatus.OK)
                .header("Access-Control-Allow-Origin", "*")
                .body(requirementDTOs);
    }

    public ResponseEntity<Void> updateRequirement(UpdateRequirementDTO updateRequirementDTO, Long id) {
        Requirement requirementToUpdate = this.getRequirementByIdAndNotDeleted(id);

        if(updateRequirementDTO.getSubject() != null){
            requirementToUpdate.setSubject(updateRequirementDTO.getSubject());
        }
        if(updateRequirementDTO.getDescription() != null){
            requirementToUpdate.setDescription(updateRequirementDTO.getDescription());
        }
        if(updateRequirementDTO.getPriority() != null){
            requirementToUpdate.setPriority(updateRequirementDTO.getPriority());
        }
        if(updateRequirementDTO.getRequirements() != null){
            requirementToUpdate.setRequirements(requirementRepository.findAllByIdsAndNotDeleted(updateRequirementDTO.getRequirements()));
        }

        requirementRepository.save(requirementToUpdate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public ResponseEntity<Void> deleteRequirement(Long id) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);
        requirement.setIsDeleted(true);
        requirementRepository.save(requirement);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public ResponseEntity<Void> assignRequirement(Long id, Long assigneeId) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);

        //Optional<User> assignee = outsideUserRepository.getOutsideUserById(assigneeId);
        User assignee = new User();
        assignee.setId(1L);
        assignee.setUsername("Usuario");
        assignee.setName("usuario interno mockeado");
        assignee.setEmail("mock@mock.com");

        if (requirement.getState() == State.CLOSED) {
            throw new TicketException("REQUIREMENT_ALREADY_CLOSED", "The requirement is already closed");
        }
        if (requirement.getState() == State.ASSIGNED) {
            throw new TicketException("REQUIREMENT_ALREADY_HAS_ASSIGNED", "The requirement is already assigned");
        }

        requirement.setAssigneeId(assigneeId);
        requirement.setState(State.ASSIGNED);
        this.sendRequirementTraceabilityEvent(Action.ASSIGN, requirement.getCode(), assignee.getId(),
                assignee.getEmail());
        requirementRepository.save(requirement);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public ResponseEntity<Void> closeRequirement(Long id) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);

        if (requirement.getState() == State.CLOSED) {
            throw new TicketException("REQUIREMENT_ALREADY_CLOSED", "The requirement is already closed");
        }

        User assignee = new User();
        assignee.setId(1L);
        assignee.setUsername("Usuario");
        assignee.setName("usuario interno mockeado");
        assignee.setEmail("mock@mock.com");

        requirement.setState(State.CLOSED);
        this.sendRequirementTraceabilityEvent(Action.CLOSE, requirement.getCode(), assignee.getId(),
                assignee.getEmail());
        requirementRepository.save(requirement);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public ResponseEntity<Void> deleteFile(Long id, List<String> fileNames) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);

        if (requirement.getFiles().isEmpty()) {
            throw new TicketException("REQUIREMENT_HAS_NO_FILES", "The requirement has no files");
        }

        for (String fileName : fileNames) {
            if (!requirement.getFiles().contains(fileName)) {
                throw new TicketException("FILE_NOT_FOUND", "The file was not found in the requirement");
            }
            fileService.deleteFile(fileName);
            requirement.getFiles().remove(fileName);
        }

        requirementRepository.save(requirement);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public ResponseEntity<Void> uploadFiles(Long id, List<MultipartFile> files) {
        Requirement requirement = this.getRequirementByIdAndNotDeleted(id);

        if(requirement.getFiles().size() + files.size() > 5){
            throw new TicketException("MAX_FILES_EXCEEDED", "The maximum number of files is 5");

        }

        List<String> sanitizedFiles = fileService.uploadFiles(files);
        requirement.getFiles().addAll(sanitizedFiles);

        requirementRepository.save(requirement);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public ResponseEntity<byte[]> downloadFile(String fileName) {
        byte[] file = fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Content-Disposition", "attachment; filename=" + fileName)
                .body(file);
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
