package com.postgres.repozytory;

import com.postgres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    // by lastname
    @Query(value = "SELECT p  WHERE p.lastName = :lastName")
    List<User> findBy(@Param("lastName") String lastName);
}
