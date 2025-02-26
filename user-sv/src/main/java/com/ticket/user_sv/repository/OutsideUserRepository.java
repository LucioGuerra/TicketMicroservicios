package com.ticket.user_sv.repository;


import com.ticket.user_sv.entity.OutsideUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OutsideUserRepository extends JpaRepository <OutsideUser, Long> {

    @Query("SELECT u FROM OutsideUser u WHERE u.status = 'active'")
    List<OutsideUser> findAllActiveUsers();

}

