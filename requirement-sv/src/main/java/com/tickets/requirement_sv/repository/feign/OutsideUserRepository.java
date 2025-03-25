package com.tickets.requirement_sv.repository.feign;


import com.tickets.requirement_sv.external.model.User;
import com.tickets.requirement_sv.repository.feign.fallback.OutsideUserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-sv",
        url = "http://user-svc:8080",
        fallbackFactory = OutsideUserFallback.class)
public interface OutsideUserRepository {

    @GetMapping("/api/v1/outside-users/{id}")
    Optional<User> getOutsideUserById(@PathVariable Long id);
}
