package com.tickets.requirement_sv.repository.fallback;

import com.tickets.requirement_sv.external.model.User;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import com.tickets.requirement_sv.repository.OutsideUserRepository;

import java.util.Optional;

public class OutsideUserFallback implements FallbackFactory<OutsideUserRepository> {
    @Override
    public OutsideUserRepository create(Throwable cause) {
        return new OutsideUserRepository() {
            @Override
            public Optional<User> getOutsideUserById(Long id) {
                if (cause instanceof FeignException.NotFound) {
                    return Optional.empty();
                }
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                throw new RuntimeException("Unhandled exception in Feign client", cause);
            }
        };
    }
}
