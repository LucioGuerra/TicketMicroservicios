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
        newUser.setStatus("active");
        OutsideUser savedUser = outsideUserRepository.save(newUser);

        // Mapear la entidad guardada a un DTO para la respuesta
        GetOutsideUserDTO responseDTO = modelMapper.map(savedUser, GetOutsideUserDTO.class);

        // Retornar la respuesta con HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //devuelve el OutsideUser con el ID
    public OutsideUser getOutsideUserById(Long id) {
        return OutsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario con id: " + id + " no encontrado"));
    }


    public ResponseEntity<List<GetOutsideUserDTO>> getAllActiveOutsideUsers() {
        List<OutsideUser> outsideUsers = outsideUserRepository.findAllActiveUsers();
        return ResponseEntity.status(HttpStatus.OK).body(outsideUsers.stream().map(OutsideUser -> modelMapper.map(OutsideUser, GetOutsideUserDTO.class)).toList());
    }

    public ResponseEntity<List<GetOutsideUserDTO>> getAllOutsideUsers() {
        List<OutsideUser> outsideUsers = outsideUserRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(outsideUsers.stream().map(OutsideUser -> modelMapper.map(OutsideUser, GetOutsideUserDTO.class)).toList());
    }

    //todo:mensaje de confirmacion en vez de void? idem anterior
    public void updateOutsideUser(Long id, OutsideUserDTO outsideUserDTO) {
        OutsideUser userToUpdate = getOutsideUserById(id);
        //todo: NO FUNCIONAN GETTERS??
        if (OutsideUserDTO.getName() != null) {
            userToUpdate.setName(OutsideUserDTO.getName());
        }
        if (OutsideUserDTO.getEmail() != null) {
            userToUpdate.setEmail(OutsideUserDTO.getEmail());
        }
        if (OutsideUserDTO.getUsername() != null) {
            userToUpdate.setUsername(OutsideUserDTO.getUsername());
        }
        if (OutsideUserDTO.getSla() != null) {
            userToUpdate.setSla(OutsideUserDTO.getSla());
        }
        if (OutsideUserDTO.getCuil() != null && userToUpdate != null) {
            ((OutsideUser) userToUpdate).setCuil(OutsideUserDTO.getCuil());
        }
        if (OutsideUserDTO.getDescription() != null && userToUpdate != null) {
            ((OutsideUser) userToUpdate).setDescription(OutsideUserDTO.getDescription());
        }
        if (OutsideUserDTO.getCompany() != null && userToUpdate != null) {
            ((OutsideUser) userToUpdate).setCompany(OutsideUserDTO.getCompany());
        }
        if (OutsideUserDTO.getStatus() != null) {
            userToUpdate.setStatus(OutsideUserDTO.getStatus());
        }
        //todo: revisar esto que puede ser null me lo marca IntelliJ
        outsideUserRepository.save(userToUpdate);
    }

    //todo: revisar si deberia ser por ID o por nombre o lo que sea
    public void deleteOutsideUserById(Long id) {
        OutsideUser user = outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado"));
        user.setStatus("deleted");
        outsideUserRepository.save(user);
    }


}
