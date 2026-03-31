package com.hancu.bankproject.system.service;

import com.hancu.bankproject.system.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role findByName(String name);

    Role save(Role role);

    void deleteById(Long id);

    List<Role> findAllEnabled();
}
