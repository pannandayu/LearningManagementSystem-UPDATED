package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    @Query(value = "SELECT r FROM Role r WHERE r.Level = (SELECT MAX(Level) FROM Role)")
    Role getMaxLevelRole();
}
