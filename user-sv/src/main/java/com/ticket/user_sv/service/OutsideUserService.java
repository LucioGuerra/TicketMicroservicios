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

    //todo: preguntar a lucio si esto es un constructor y esta bien hecho
    @Autowired
    public OutsideUserService(OutsideUserRepository outsideUserRepository, ModelMapper modelMapper) {
        this.outsideUserRepository = outsideUserRepository;
        this.modelMapper = modelMapper;
    }

    //todo: preguntar a lucio si esto es como le gusta :)
    public ResponseEntity<GetOutsideUserDTO> createOutsideUser(OutsideUserDTO outsideUserDTO) {
        // Mapear el DTO a la entidad
        OutsideUser newUser = modelMapper.map(outsideUserDTO, OutsideUser.class);
        newUser.setStatus(true);
        OutsideUser savedUser = outsideUserRepository.save(newUser);

        // Mapear la entidad guardada a un DTO para la respuesta
        GetOutsideUserDTO responseDTO = modelMapper.map(savedUser, GetOutsideUserDTO.class);

        // Retornar la respuesta con HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //devuelve el OutsideUser con el ID
    public OutsideUser getOutsideUserById(Long id) {
        return outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user with id: " + id + " not found"));
    }


    public ResponseEntity<List<GetOutsideUserDTO>> getAllActiveOutsideUsers() {
        List<OutsideUser> outsideUsers = outsideUserRepository.findAllActiveUsers();
        return ResponseEntity.status(HttpStatus.OK).body(outsideUsers.stream().map(OutsideUser -> modelMapper.map(OutsideUser, GetOutsideUserDTO.class)).toList());
    }

    public ResponseEntity<List<GetOutsideUserDTO>> getAllOutsideUsers() {
        List<OutsideUser> outsideUsers = outsideUserRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(outsideUsers.stream().map(OutsideUser -> modelMapper.map(OutsideUser, GetOutsideUserDTO.class)).toList());
    }


    public ResponseEntity<GetOutsideUserDTO> updateOutsideUser(Long id, OutsideUserDTO outsideUserDTO) {

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
        if (outsideUserDTO.getStatus() != null) {
            userToUpdate.setStatus(outsideUserDTO.getStatus());
        }
        OutsideUser updatedUser = outsideUserRepository.save(userToUpdate);

        GetOutsideUserDTO responseDTO = modelMapper.map(updatedUser, GetOutsideUserDTO.class);

        return ResponseEntity.ok(responseDTO);
    }


    //todo: revisar si deberia ser por ID o por nombre o lo que sea
    public void deleteOutsideUserById(Long id) {
        OutsideUser user = outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user with id: " + id + " not found"));
        user.setStatus(false);
        outsideUserRepository.save(user);
    }

    //todo: funcion con un usuario devolver todos los requerimientos que tenga asociados

}
