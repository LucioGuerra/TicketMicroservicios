package com.tickets.authorization_sv.service;

import com.tickets.authorization_sv.dto.UserDTO;
import com.tickets.authorization_sv.entity.Role;
import com.tickets.authorization_sv.entity.User;
import com.tickets.authorization_sv.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<?> registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
