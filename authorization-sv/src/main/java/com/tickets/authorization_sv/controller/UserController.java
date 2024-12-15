package com.tickets.authorization_sv.controller;

import com.tickets.authorization_sv.dto.UserDTO;
import com.tickets.authorization_sv.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/public/users")
public class UserController {

        private final UserService userService;

        @PostMapping("/register")
        public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
            return userService.registerUser(userDTO);
        }
}
