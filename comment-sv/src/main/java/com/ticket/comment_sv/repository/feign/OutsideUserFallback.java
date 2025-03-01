package com.ticket.comment_sv.repository.feign;

import com.ticket.comment_sv.external.model.User;
import com.ticket.comment_sv.repository.OutsideUserRepository;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OutsideUserFallback implements FallbackFactory<OutsideUserRepository> {
    @Override
    public OutsideUserRepository create(Throwable cause) {
        return new OutsideUserRepository() {
            @Override
            public Optional<User> getUserById(Long id) {
                if (cause instanceof FeignException.NotFound) {
                    return Optional.empty();
                }
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException) cause;
                }
                throw new RuntimeException("Unhandled exception in Feign client", cause);
            }

            @Override
            public List<User> getUsersByIds(List<Long> ids) {
                return List.of();
            }
        };
    }
}
