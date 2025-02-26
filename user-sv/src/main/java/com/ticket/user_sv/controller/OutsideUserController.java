package com.ticket.user_sv.controller;

import com.ticket.user_sv.DTO.request.OutsideUserDTO;
import com.ticket.user_sv.DTO.response.GetOutsideUserDTO;
import com.ticket.user_sv.service.OutsideUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<GetOutsideUserDTO> getOutsideUserById(@PathVariable Long id) {
        return outsideUserService.getOutsideUserById(id);
    }

    // Obtener todos los usuarios activos
    @GetMapping("/active")
    public ResponseEntity<Page<GetOutsideUserDTO>> getAllActiveOutsideUsers(@PageableDefault(size = 10) Pageable pageable,
                                                                            @RequestParam(required = false) String name,
                                                                            @RequestParam(required = false) String email,
                                                                            @RequestParam(required = false) String company,
                                                                            @RequestParam(required = false) String cuil,
                                                                            @RequestParam(required = false) Boolean sla,
                                                                            @RequestParam(required = false) String username,
                                                                            @RequestParam(required = false) Boolean isDeleted) {
        return outsideUserService.getAllOutsideUsers(pageable, name, email, company, cuil, sla, username, isDeleted);
    }

    // Actualizar un usuario externo
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOutsideUser(@PathVariable Long id, @RequestBody OutsideUserDTO outsideUserDTO) {
        outsideUserService.updateOutsideUser(id, outsideUserDTO);
        return ResponseEntity.ok("user updated correctly.");
    }

    // Eliminar un usuario externo (borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOutsideUserById(@PathVariable Long id) {
        outsideUserService.deleteOutsideUserById(id);
        return ResponseEntity.ok("user deleted correctly.");
    }
}
