package com.tickets.requirement_sv.controller;

import com.tickets.requirement_sv.Annotation.MaxFileListSize;
import com.tickets.requirement_sv.dto.GetRequirementDTO;
import com.tickets.requirement_sv.dto.RequirementDTO;
import com.tickets.requirement_sv.dto.UpdateRequirementDTO;
import com.tickets.requirement_sv.entity.Priority;
import com.tickets.requirement_sv.entity.State;
import com.tickets.requirement_sv.service.RequirementService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/requirements")
public class RequirementController {

    private final RequirementService requirementService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<GetRequirementDTO> createRequirement(@RequestPart("requirement") @Valid RequirementDTO requirementDTO,
                                                  @RequestPart(value = "files", required = false) @MaxFileListSize(max = 5) List<MultipartFile> files) {
           return requirementService.createRequirement(requirementDTO, files);
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

    @PatchMapping("/{id}/assign")
    public ResponseEntity<Void> assignRequirement(@PathVariable Long id, @RequestParam Long assigneeId) {
        return requirementService.assignRequirement(id, assigneeId);
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<Void> closeRequirement(@PathVariable Long id) {
        return requirementService.closeRequirement(id);
    }

    @DeleteMapping("/{id}/files")
    public ResponseEntity<Void> deleteFiles(@PathVariable Long id, @RequestParam("fileName") List<String> fileNames) {
        return requirementService.deleteFile(id, fileNames);
    }

    @PostMapping("/{id}/files")
    public ResponseEntity<Void> uploadFiles(@PathVariable Long id, @RequestPart("files") @MaxFileListSize(max = 5) List<MultipartFile> files) {
        return requirementService.uploadFiles(id, files);
    }

    @GetMapping(path = "/files", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getFiles(@RequestParam("fileName") String fileName) {
        return requirementService.downloadFile(fileName);
    }

}
