package com.tickets.type_sv.repository;

import com.tickets.type_sv.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query("SELECT t FROM Type t WHERE t.deleted = false")
    List<Type> findAllAndNotDeleted();

    @Query("SELECT t FROM Type t WHERE t.id = :id AND t.deleted = false")
    Optional<Type> findByIdAndNotDeleted(Long id);
}
