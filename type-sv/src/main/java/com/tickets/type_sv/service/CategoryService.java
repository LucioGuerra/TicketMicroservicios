package com.tickets.type_sv.service;

import com.tickets.type_sv.dto.request.CategoryDTO;
import com.tickets.type_sv.dto.request.UpdateCategoryDTO;
import com.tickets.type_sv.dto.response.GetCategoryDTO;
import com.tickets.type_sv.entity.Category;
import com.tickets.type_sv.entity.Type;
import com.tickets.type_sv.event.DeleteCategoriesByTypeEvent;
import com.tickets.type_sv.exception.TicketException;
import com.tickets.type_sv.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TypeService typeService;
    private final ModelMapper modelMapper;

    @EventListener
    @Transactional
    public void handlerDeleteCategoriesByTypeEvent(DeleteCategoriesByTypeEvent event) {
        List<Category> categories = categoryRepository.findByTypeIdAndNotDeleted(event.typeId());
        categories.forEach(category -> {
            category.setDeleted(true);
            categoryRepository.save(category);
        });
    }

    public ResponseEntity<Void> createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

        Type type = typeService.getTypeById(categoryDTO.getTypeId());
        category.setType(type);

        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<GetCategoryDTO> getCategoryById(Long id) {
        Category category = categoryRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        GetCategoryDTO categoryDTO = modelMapper.map(category, GetCategoryDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    public ResponseEntity<List<GetCategoryDTO>> getAllCategories() {
        List<Category> categories = categoryRepository.findAllNotDeleted();
        List<GetCategoryDTO> categoriesDTOs = categories.stream().map(category -> modelMapper.map(category, GetCategoryDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(categoriesDTOs);
    }

    public ResponseEntity<Void> updateCategory(Long id, UpdateCategoryDTO categoryDTO) {
        Category category = categoryRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException("Category " +
                "not found with id: " + id));

        if (categoryDTO.getDescription() != null) {
            category.setDescription(categoryDTO.getDescription());
        }
        if (categoryDTO.getTypeId() != null) {
            Type type = typeService.getTypeById(categoryDTO.getTypeId());
            category.setType(type);
        }

        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Transactional
    public ResponseEntity<Void> deleteCategory(Long id) {
        Category category = categoryRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new EntityNotFoundException("Category " +
                "not found with id: " + id));
        if (category.getDeleted()) {
            throw new TicketException("CATEGORY_ALREADY_DELETED", "Category already deleted");
        }

        category.setDeleted(true);
        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Boolean> validateCategory(Long id) {
        Boolean exists = categoryRepository.findByIdAndNotDeleted(id)
                .map(category -> !category.getDeleted())
                .orElse(false);

        return ResponseEntity.status(HttpStatus.OK).body(exists);
    }
}
