package com.ticket.user_sv.controller;

import com.ticket.user_sv.DTO.request.OutsideUserDTO;
import com.ticket.user_sv.DTO.response.GetOutsideUserDTO;
import com.ticket.user_sv.entity.OutsideUser;
import com.ticket.user_sv.service.OutsideUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/v1/outside-users")
public class OutsideUserController {

    @Autowired
    private OutsideUserService outsideUserService;

    // Crear un nuevo usuario externo
    @PostMapping
    public ResponseEntity<GetOutsideUserDTO> createOutsideUser(@RequestBody OutsideUserDTO outsideUserDTO) {
        return outsideUserService.createOutsideUser(outsideUserDTO);
    }

    // Obtener un usuario externo por ID
    @GetMapping("/{id}")
    public ResponseEntity<OutsideUser> getOutsideUserById(@PathVariable Long id) {
        OutsideUser user = outsideUserService.getOutsideUserById(id);
        return ResponseEntity.ok(user);
    }

    // Obtener todos los usuarios activos
    @GetMapping("/active")
    public ResponseEntity<List<GetOutsideUserDTO>> getAllActiveOutsideUsers() {
        return outsideUserService.getAllActiveOutsideUsers();
    }

    // Obtener todos los usuarios (incluyendo eliminados)
    @GetMapping
    public ResponseEntity<List<GetOutsideUserDTO>> getAllOutsideUsers() {
        return outsideUserService.getAllOutsideUsers();
    }

    // Actualizar un usuario externo
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOutsideUser(@PathVariable Long id, @RequestBody OutsideUserDTO outsideUserDTO) {
        outsideUserService.updateOutsideUser(id, outsideUserDTO);
        return ResponseEntity.ok("Usuario actualizado correctamente.");
    }

    // Eliminar un usuario externo (borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOutsideUserById(@PathVariable Long id) {
        outsideUserService.deleteOutsideUserById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }
}
