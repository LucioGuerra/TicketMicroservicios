package com.tickets.type_sv.controller;

import com.tickets.type_sv.dto.request.TypeDTO;
import com.tickets.type_sv.dto.request.UpdateTypeDTO;
import com.tickets.type_sv.dto.response.GetTypeDTO;
import com.tickets.type_sv.service.TypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/public/types")
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    public ResponseEntity<Void> createType(@RequestBody @Valid TypeDTO typeDTO) {
        return typeService.createType(typeDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTypeDTO> getType(@PathVariable Long id) {
        return typeService.getTypeDTOById(id);
    }

    @GetMapping
    public ResponseEntity<List<GetTypeDTO>> getTypes() {
        return typeService.getAllTypes();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateType(@PathVariable Long id, @Valid @RequestBody UpdateTypeDTO updateTypeDTO) {
        return typeService.updateType(id, updateTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        return typeService.deleteType(id);
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateType(@PathVariable Long id) {
        return typeService.validateType(id);
    }
}
