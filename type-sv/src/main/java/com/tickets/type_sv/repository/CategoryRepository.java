package com.tickets.type_sv.repository;

import com.tickets.type_sv.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category AS c WHERE c.id=:id AND c.deleted=false")
    Optional<Category> findByIdAndNotDeleted(Long id);

    @Query("SELECT c FROM Category AS c WHERE c.deleted=false")
    List<Category> findAllNotDeleted();

    @Query("SELECT c FROM Category AS c WHERE c.type.id=:typeId AND c.deleted=false")
    List<Category> findByTypeIdAndNotDeleted(Long typeId);
}
