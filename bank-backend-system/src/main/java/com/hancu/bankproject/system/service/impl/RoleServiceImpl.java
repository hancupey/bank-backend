package com.hancu.bankproject.system.service.impl;

import com.hancu.bankproject.common.exception.BusinessException;
import com.hancu.bankproject.system.domain.Role;
import com.hancu.bankproject.system.mapper.RoleMapper;
import com.hancu.bankproject.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public Role findById(Long id) {
        Role role = roleMapper.selectById(id);
        if (role == null) {
            throw new BusinessException("角色不存在");
        }
        return role;
    }

    @Override
    public Role findByName(String name) {
        Role role = roleMapper.selectByName(name);
        if (role == null) {
            throw new BusinessException("角色不存在");
        }
        return role;
    }

    @Override
    @Transactional
    public Role save(Role role) {
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new BusinessException("角色名称不能为空");
        }
        if (role.getId() == null) {
            roleMapper.insert(role);
        } else {
            roleMapper.updateById(role);
        }
        return role;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (roleMapper.selectById(id) == null) {
            throw new BusinessException("角色不存在");
        }
        roleMapper.deleteById(id);
    }

    @Override
    public List<Role> findAllEnabled() {
        return roleMapper.selectAllEnabled();
    }
}
