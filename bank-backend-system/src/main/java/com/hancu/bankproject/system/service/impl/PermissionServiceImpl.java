package com.hancu.bankproject.system.service.impl;

import com.hancu.bankproject.common.exception.BusinessException;
import com.hancu.bankproject.system.domain.Permission;
import com.hancu.bankproject.system.mapper.PermissionMapper;
import com.hancu.bankproject.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectList(null);
    }

    @Override
    public Permission findById(Long id) {
        Permission permission = permissionMapper.selectById(id);
        if (permission == null) {
            throw new BusinessException("权限不存在");
        }
        return permission;
    }

    @Override
    public Permission findByCode(String code) {
        Permission permission = permissionMapper.selectByCode(code);
        if (permission == null) {
            throw new BusinessException("权限不存在");
        }
        return permission;
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        if (permission.getName() == null || permission.getName().trim().isEmpty()) {
            throw new BusinessException("权限名称不能为空");
        }
        if (permission.getCode() == null || permission.getCode().trim().isEmpty()) {
            throw new BusinessException("权限编码不能为空");
        }
        if (permission.getId() == null) {
            permissionMapper.insert(permission);
        } else {
            permissionMapper.updateById(permission);
        }
        return permission;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (permissionMapper.selectById(id) == null) {
            throw new BusinessException("权限不存在");
        }
        permissionMapper.deleteById(id);
    }

    @Override
    public List<Permission> findAllEnabled() {
        return permissionMapper.selectAllEnabled();
    }
}
