package com.tickets.type_sv.controller;

import com.tickets.type_sv.dto.request.UpdateCategoryDTO;
import com.tickets.type_sv.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/public/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory() {
        return categoryService.createCategory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public ResponseEntity<Void> getCategories() {
        return categoryService.getAllCategories();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        return categoryService.updateCategory(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
