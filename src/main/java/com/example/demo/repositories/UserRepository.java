package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "SELECT u FROM User u WHERE u.id = ?1")
    User findUserById(Integer id);
    
}
