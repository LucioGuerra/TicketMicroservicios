package com.tickets.requirement_sv.repository;


import com.tickets.requirement_sv.external.model.User;
import com.tickets.requirement_sv.repository.fallback.OutsideUserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-sv", fallbackFactory = OutsideUserFallback.class)
public interface OutsideUserRepository {

    @GetMapping("/apí/v1/users/{id}")
    Optional<User> getOutsideUserById(@PathVariable Long id);
}
