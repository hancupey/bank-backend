package com.hancu.bankproject.system.service;

import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.system.domain.User;

import java.util.List;

public interface UserService {

    PageResponse<User> findAll(int pageNum, int pageSize);

    User findById(Long id);

    User findByUsername(String username);

    User save(User user);

    void deleteById(Long id);

    boolean existsByUsername(String username);

    List<User> findByOrganizationId(Long orgId);
}
