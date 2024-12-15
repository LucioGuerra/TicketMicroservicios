package com.tickets.type_sv.service;

import com.tickets.type_sv.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<Void> createCategory() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> getCategoryById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> updateCategory(Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<Void> deleteCategory(Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
