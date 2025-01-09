package com.ticket.user_sv.service;

import com.ticket.user_sv.dto.request.UserDTO;
import com.ticket.user_sv.dto.response.GetUserDTO;
import com.ticket.user_sv.entity.OutsideUser;;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    //crea un usuario
    public OutsideUser createUser(OutsideUserDTO userDTO) {
        OutsideUser user = mapToEntity(userDTO);
        return repository.save(user);
    }

    /**
     * Obtiene un usuario por su ID y devuelve un DTO de respuesta.
     */
    public GetUserDTO getUserDTOById(Long id) {
        User user = getUserById(id);
        return modelMapper.map(user, GetUserDTO.class);
    }

    /**
     * Obtiene todos los usuarios y los convierte en una lista de DTOs de respuesta.
     */
    public List<GetUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, GetUserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un usuario existente con los datos proporcionados en el DTO.
     */
    public GetUserDTO updateUser(Long id, UserDTO userDTO) {
        User userToUpdate = getUserById(id);

        if (userDTO.getName() != null) {
            userToUpdate.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            userToUpdate.setEmail(userDTO.getEmail());
        }
        if (userDTO.getUsername() != null) {
            userToUpdate.setUsername(userDTO.getUsername());
        }
        if (userDTO.getPosition() != null) {
            userToUpdate.setPosition(userDTO.getPosition());
        }
        if (userDTO.getDepartment() != null) {
            userToUpdate.setDepartment(userDTO.getDepartment());
        }
        if (userDTO.getUserFile() != null) {
            userToUpdate.setUserFile(userDTO.getUserFile());
        }
        if (userDTO.getSla() != null) {
            userToUpdate.setSla(userDTO.getSla());
        }

        // Manejo de campos específicos para OutsideUser
        if (userToUpdate instanceof OutsideUser) {
            OutsideUser outsideUser = (OutsideUser) userToUpdate;
            if (userDTO.getCuil() != null) {
                outsideUser.setCuil(userDTO.getCuil());
            }
            if (userDTO.getDescription() != null) {
                outsideUser.setDescription(userDTO.getDescription());
            }
            if (userDTO.getCompany() != null) {
                outsideUser.setCompany(userDTO.getCompany());
            }
        }

        User updatedUser = userRepository.save(userToUpdate);
        return modelMapper.map(updatedUser, GetUserDTO.class);
    }

    //Elimina usuario por ID
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("El usuario con ID " + id + " no existe.");
        }
        userRepository.deleteById(id);
    }

    //Obtiene un usuario por su ID.
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con ID " + id + " no encontrado."));
    }
}

