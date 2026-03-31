package com.hancu.bankproject.system.service;

import com.hancu.bankproject.system.domain.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> findAll();

    Organization findById(Long id);

    Organization save(Organization organization);

    void deleteById(Long id);

    List<Organization> findByParentId(Long parentId);

    List<Organization> findAllEnabled();
}
