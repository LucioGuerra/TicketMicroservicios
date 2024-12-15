package com.tickets.type_sv.controller;

import com.tickets.type_sv.dto.request.TypeDTO;
import com.tickets.type_sv.dto.request.UpdateTypeDTO;
import com.tickets.type_sv.service.TypeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/public/types")
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    public ResponseEntity<Void> createType(@RequestBody @Valid TypeDTO TypeDTO) {
        return typeService.createType(new TypeDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getType(@PathVariable Long id) {
        return typeService.getTypeById(id);
    }

    @GetMapping
    public ResponseEntity<Void> getTypes() {
        return typeService.getAllTypes();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateType(@PathVariable Long id, @Valid @RequestBody UpdateTypeDTO updateTypeDTO) {
        return typeService.updateType(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Long id) {
        return typeService.deleteType(id);
    }
}
