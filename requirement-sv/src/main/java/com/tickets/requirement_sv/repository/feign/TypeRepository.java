package com.tickets.requirement_sv.repository.feign;

import com.tickets.requirement_sv.external.model.Category;
import com.tickets.requirement_sv.external.model.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "type-sv")
public interface TypeRepository {

    @GetMapping("/api/types/{id}")
    Type getTypeById(@PathVariable Long id);

    @GetMapping("/api/types")
    List<Type> getAllTypes();

    @GetMapping("/api/types/validate/{id}")
    Boolean validateType(@PathVariable Long id);

    @GetMapping("/api/categories/{id}")
    Category getCategoryById(@PathVariable Long id);

    @GetMapping("/api/categories/validate/{id}")
    Boolean validateCategory(@PathVariable Long id);
}
