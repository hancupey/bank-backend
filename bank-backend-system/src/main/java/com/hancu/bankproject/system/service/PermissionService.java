package com.hancu.bankproject.system.service;

import com.hancu.bankproject.system.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    Permission findById(Long id);

    Permission findByCode(String code);

    Permission save(Permission permission);

    void deleteById(Long id);

    List<Permission> findAllEnabled();
}
