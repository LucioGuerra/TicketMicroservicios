package com.tickets.requirement_sv.repository;

import com.tickets.requirement_sv.external.model.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "type-sv", url = "http://localhost:8081/public")
public interface TypeRepository {

    @GetMapping("/types/{id}")
    Type getTypeById(@PathVariable String id);

    @GetMapping("/types")
    List<Type> getAllTypes();
}
