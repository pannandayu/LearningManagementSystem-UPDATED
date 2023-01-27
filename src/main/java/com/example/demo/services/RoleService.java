package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Role;

public interface RoleService {
    public List<Role> getAll();
    public Role getById(Integer id);
    public Boolean save(Role role);
    public Boolean delete(Integer id);
    public Role getMaxLevelRole();
}
