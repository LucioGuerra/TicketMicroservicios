package com.ticket.user_sv.service;


import com.ticket.user_sv.DTO.request.OutsideUserDTO;
import com.ticket.user_sv.DTO.response.GetOutsideUserDTO;
import com.ticket.user_sv.entity.OutsideUser;
import com.ticket.user_sv.mapper.OutsideUserMapper;
import com.ticket.user_sv.repository.OutsideUserRepository;
import com.ticket.user_sv.specification.UserSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OutsideUserService {

    private final OutsideUserRepository outsideUserRepository;
    private final OutsideUserMapper outsideUserMapper;


    public ResponseEntity<GetOutsideUserDTO> createOutsideUser(OutsideUserDTO outsideUserDTO) {

        OutsideUser newUser = outsideUserMapper.toEntity(outsideUserDTO);
        OutsideUser savedUser = outsideUserRepository.save(newUser);

        GetOutsideUserDTO responseDTO = outsideUserMapper.toDTO(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Access-Control-Allow-Origin", "*")
                .body(responseDTO);
    }

    public ResponseEntity<GetOutsideUserDTO> getOutsideUserById(Long id) {
        OutsideUser outsideUser = outsideUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
        GetOutsideUserDTO responseDTO = outsideUserMapper.toDTO(outsideUser);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<Page<GetOutsideUserDTO>> getAllOutsideUsers(Pageable pageable, String name, String email, String company,String cuil,Boolean sla,String username, Boolean isDeleted) {

        Specification<OutsideUser> spec = Specification.where(UserSpecification.isDeleted(true))
                .and(UserSpecification.byName(name))
                .and(UserSpecification.byEmail(email))
                .and((UserSpecification.byCompany(company))
                .and(UserSpecification.byCuil(cuil))
                .and(UserSpecification.bySla(sla))
                .and(UserSpecification.byUsername(username))
                .and(UserSpecification.isDeleted(isDeleted)));

        Page<OutsideUser> userPage = outsideUserRepository.findAll(spec, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .header("Access-Control-Allow-Origin", "*")
                .body(userPage.map(outsideUserMapper::toDTO));
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