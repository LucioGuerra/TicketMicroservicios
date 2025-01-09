package com.ticket.user_sv.controller;


import com.ticket.user_sv.entity.OutsideUser;
import com.ticket.user_sv.service.OutsideUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/outside-users")
public class OutsideUserController {

    private final OutsideUserService outsideUserService;

    @PostMapping
    public void createUser(@RequestBody UserDTO userDTO) {
        OutsideUserService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutsideUser> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<OutsideUser>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutsideUser> updateUser(@PathVariable Long id, @RequestBody OutsideUserDTO userDTO) {
        return ResponseEntity.ok(service.updateUser(id, userDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}