package com.tickets.requirement_sv.repository;

import com.tickets.requirement_sv.external.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-sv", url = "http://localhost:8081/public/categories")
public interface CategoryRepository {
    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable String id);


}
