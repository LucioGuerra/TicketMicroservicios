package com.tickets.traceability_sv.controller;

import com.tickets.traceability_sv.dto.GetTraceabilityDTO;
import com.tickets.traceability_sv.service.RequirementTraceabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/traceability")
public class RequirementTraceabilityController {

    private final RequirementTraceabilityService requirementTraceabilityService;

    @GetMapping
    public ResponseEntity<List<GetTraceabilityDTO>> getTraceability(@RequestParam("code") String code) {
        return requirementTraceabilityService.findByCode(code);
    }
}
