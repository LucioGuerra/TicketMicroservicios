package com.ticket.user_sv.service;


import com.ticket.user_sv.DTO.request.OutsideUserDTO;
import com.ticket.user_sv.DTO.response.GetOutsideUserDTO;
import com.ticket.user_sv.entity.OutsideUser;
import com.ticket.user_sv.repository.OutsideUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutsideUserService {

    private final OutsideUserRepository outsideUserRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OutsideUserService(OutsideUserRepository outsideUserRepository, ModelMapper modelMapper) {
        this.outsideUserRepository = outsideUserRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<GetOutsideUserDTO> createOutsideUser(OutsideUserDTO outsideUserDTO) {

        OutsideUser newUser = modelMapper.map(outsideUserDTO, OutsideUser.class);
        OutsideUser savedUser = outsideUserRepository.save(newUser);

        GetOutsideUserDTO responseDTO = modelMapper.map(savedUser, GetOutsideUserDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    public ResponseEntity<GetOutsideUserDTO> getOutsideUserById(Long id) {
        OutsideUser outsideUser = outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
        GetOutsideUserDTO responseDTO = modelMapper.map(outsideUser, GetOutsideUserDTO.class);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<List<GetOutsideUserDTO>> getAllOutsideUsers() {
        List<OutsideUser> outsideUsers = outsideUserRepository.findAllActiveUsers();
        return ResponseEntity.status(HttpStatus.OK).body(outsideUsers.stream().map(OutsideUser -> modelMapper.map(OutsideUser, GetOutsideUserDTO.class)).toList());
    }

    public void updateOutsideUser(Long id, OutsideUserDTO outsideUserDTO) {

        OutsideUser userToUpdate = outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user with id: " + id + " not found"));

        if (outsideUserDTO.getName() != null) {
            userToUpdate.setName(outsideUserDTO.getName());
        }
        if (outsideUserDTO.getEmail() != null) {
            userToUpdate.setEmail(outsideUserDTO.getEmail());
        }
        if (outsideUserDTO.getUsername() != null) {
            userToUpdate.setUsername(outsideUserDTO.getUsername());
        }
        if (outsideUserDTO.getCompany() != null) {
            userToUpdate.setCompany(outsideUserDTO.getCompany());
        }
        if (outsideUserDTO.getDescription() != null) {
            userToUpdate.setDescription(outsideUserDTO.getDescription());
        }
        if (outsideUserDTO.getCuil() != null) {
            userToUpdate.setCuil(outsideUserDTO.getCuil());
        }
        if (outsideUserDTO.getActive() != null) {
            userToUpdate.setActive(outsideUserDTO.getActive());
        }

        outsideUserRepository.save(userToUpdate);
    }

    public void deleteOutsideUserById(Long id) {
        OutsideUser user = outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user with id: " + id + " not found"));
        user.setActive(false);
        outsideUserRepository.save(user);
    }
    
}