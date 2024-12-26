package com.tickets.traceability_sv.service;

import com.tickets.traceability_sv.dto.GetTraceabilityDTO;
import com.tickets.traceability_sv.entity.RequirementTraceability;
import com.tickets.traceability_sv.event.RequirementTracebilityEvent;
import com.tickets.traceability_sv.repository.RequirementTraceabilityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RequirementTraceabilityService {

    private final RequirementTraceabilityRepository requirementTraceabilityRepository;
    private final ModelMapper modelMapper;

    @KafkaListener(topics = "requirement-traceability", groupId = "traceability-sv")
    public void saveAction(RequirementTracebilityEvent event) {
        RequirementTraceability traceability = modelMapper.map(event, RequirementTraceability.class);
        requirementTraceabilityRepository.save(traceability);
    }

    public ResponseEntity<List<GetTraceabilityDTO>> findByCode(String code) {
        List<RequirementTraceability> traceability =
                requirementTraceabilityRepository.findByCode(code);

        if (traceability.isEmpty()) {
            throw new EntityNotFoundException("Requirement traceability not found");
        }

        List<GetTraceabilityDTO> traceabilityDTO =
                traceability.stream()
                        .map(trace -> modelMapper.map(trace, GetTraceabilityDTO.class))
                        .toList();

        return ResponseEntity.status(HttpStatus.OK).body(traceabilityDTO);
    }
}
