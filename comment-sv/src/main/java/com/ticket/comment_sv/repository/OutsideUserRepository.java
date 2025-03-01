package com.ticket.comment_sv.repository;

import com.ticket.comment_sv.external.model.User;
import com.ticket.comment_sv.repository.feign.OutsideUserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@FeignClient(name = "user-sv", fallbackFactory = OutsideUserFallback.class)
public interface OutsideUserRepository {

    @GetMapping("/api/v1/users/{id}")
    Optional<User> getUserById(Long id);

    @GetMapping("/api/v1/users/ids")
    List<User> getUsersByIds(Set<Long> ids);

}
