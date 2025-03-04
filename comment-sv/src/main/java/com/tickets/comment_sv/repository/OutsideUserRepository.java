package com.tickets.comment_sv.repository;

import com.tickets.comment_sv.external.model.User;
import com.tickets.comment_sv.repository.feign.OutsideUserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@FeignClient(name = "user-sv", fallbackFactory = OutsideUserFallback.class)
public interface OutsideUserRepository {

    @GetMapping("/api/v1/outside-users/{id}")
    Optional<User> getUserById(@PathVariable Long id);

    @GetMapping("/api/v1/outside-users/ids")
    List<User> getUsersByIds(@RequestParam Set<Long> ids);

}
