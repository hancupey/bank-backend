package com.hancu.bankproject.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.common.exception.BusinessException;
import com.hancu.bankproject.system.domain.User;
import com.hancu.bankproject.system.mapper.UserMapper;
import com.hancu.bankproject.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResponse<User> findAll(int pageNum, int pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        IPage<User> result = userMapper.selectPage(page, null);
        return new PageResponse<>(result.getTotal(), result.getRecords());
    }

    @Override
    public User findById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return user;
    }

    @Override
    @Transactional
    public User save(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }

        if (user.getId() == null && userMapper.existsByUsername(user.getUsername())) {
            throw new BusinessException("用户名已存在");
        }

        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (userMapper.selectById(id) == null) {
            throw new BusinessException("用户不存在");
        }
        userMapper.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.existsByUsername(username);
    }

    @Override
    public List<User> findByOrganizationId(Long orgId) {
        return userMapper.selectByOrgId(orgId);
    }
}
