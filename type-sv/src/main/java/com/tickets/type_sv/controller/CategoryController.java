package com.tickets.type_sv.controller;

import com.tickets.type_sv.dto.request.CategoryDTO;
import com.tickets.type_sv.dto.request.UpdateCategoryDTO;
import com.tickets.type_sv.dto.response.GetCategoryDTO;
import com.tickets.type_sv.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCategoryDTO> getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public ResponseEntity<List<GetCategoryDTO>> getCategories() {
        return categoryService.getAllCategories();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) {
        return categoryService.updateCategory(id, updateCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateCategory(@PathVariable Long id) {
        return categoryService.validateCategory(id);
    }
}
