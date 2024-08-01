package com.postgres.Repo;

import com.postgres.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {


    // by lastname
    @Query(value = "SELECT p FROM Person p WHERE p.lastName = :lastName")
    List<Person> findPersonsByLastName(@Param("lastName") String lastName);
}
