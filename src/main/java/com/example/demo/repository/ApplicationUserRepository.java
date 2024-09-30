package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Integer> {

    @Query(value = "SELECT * FROM Application_User AS AU WHERE AU.username = ?1", nativeQuery = true)
    Optional<ApplicationUser> findByUsername(String username);
}
