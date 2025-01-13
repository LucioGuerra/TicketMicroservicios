package com.tickets.type_sv.service;

import com.tickets.type_sv.dto.request.TypeDTO;
import com.tickets.type_sv.dto.request.UpdateTypeDTO;
import com.tickets.type_sv.dto.response.GetTypeDTO;
import com.tickets.type_sv.entity.Type;
import com.tickets.type_sv.event.DeleteCategoriesByTypeEvent;
import com.tickets.type_sv.exception.TicketException;
import com.tickets.type_sv.repository.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class TypeService {

    private final TypeRepository typeRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final ModelMapper modelMapper;

    public ResponseEntity<Void> createType(TypeDTO typeDTO) {
        Type type = modelMapper.map(typeDTO, Type.class);
        typeRepository.save(type);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<GetTypeDTO> getTypeDTOById(Long id) {
        Type type = typeRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException("Type not found with id: " + id));
        GetTypeDTO typeDTO= modelMapper.map(type, GetTypeDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(typeDTO);
    }

    public ResponseEntity<List<GetTypeDTO>> getAllTypes() {
        List<Type> types = typeRepository.findAllAndNotDeleted();
        List<GetTypeDTO> typesDTOs = types.stream().map(type -> modelMapper.map(type, GetTypeDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(typesDTOs);
    }

    public ResponseEntity<Void> updateType(Long id, UpdateTypeDTO typeDTO) {
        Type type = typeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Type not found with id: " + id));

        if (typeDTO.getCode() != null) {
            type.setCode(typeDTO.getCode());
        }
        if (typeDTO.getDescription() != null) {
            type.setDescription(typeDTO.getDescription());
        }

        typeRepository.save(type);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    public ResponseEntity<Void> deleteType(Long id) {
        Type type = typeRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException("Type not found with" +
                " id: " + id));
        if (type.getDeleted()){
            throw new TicketException("TYPE_ALREADY_DELETED", "Type already deleted");
        }


        eventPublisher.publishEvent(new DeleteCategoriesByTypeEvent(type.getId()));
        type.setDeleted(true);
        typeRepository.save(type);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Boolean> validateType(Long id) {
        boolean exists = typeRepository.findByIdAndNotDeleted(id)
                .map(category -> !category.getDeleted())
                .orElse(false);
        return ResponseEntity.status(HttpStatus.OK).body(exists);
    }

    public Type getTypeById(Long id) {
        return typeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Type not found"));
    }
}
