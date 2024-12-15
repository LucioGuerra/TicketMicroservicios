package com.tickets.type_sv.service;

import com.tickets.type_sv.dto.request.TypeDTO;
import com.tickets.type_sv.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public ResponseEntity<Void> createType(TypeDTO typeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> getTypeById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> getAllTypes() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> updateType(Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> deleteType(Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
