package com.hancu.bankproject.system.service.impl;

import com.hancu.bankproject.common.exception.BusinessException;
import com.hancu.bankproject.system.domain.Organization;
import com.hancu.bankproject.system.mapper.OrganizationMapper;
import com.hancu.bankproject.system.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> findAll() {
        return organizationMapper.selectList(null);
    }

    @Override
    public Organization findById(Long id) {
        Organization organization = organizationMapper.selectById(id);
        if (organization == null) {
            throw new BusinessException("机构不存在");
        }
        return organization;
    }

    @Override
    @Transactional
    public Organization save(Organization organization) {
        if (organization.getName() == null || organization.getName().trim().isEmpty()) {
            throw new BusinessException("机构名称不能为空");
        }
        if (organization.getId() == null) {
            organizationMapper.insert(organization);
        } else {
            organizationMapper.updateById(organization);
        }
        return organization;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (organizationMapper.selectById(id) == null) {
            throw new BusinessException("机构不存在");
        }
        List<Organization> children = organizationMapper.selectByParentId(id);
        if (!children.isEmpty()) {
            throw new BusinessException("请先删除子机构");
        }
        organizationMapper.deleteById(id);
    }

    @Override
    public List<Organization> findByParentId(Long parentId) {
        return organizationMapper.selectByParentId(parentId);
    }

    @Override
    public List<Organization> findAllEnabled() {
        return organizationMapper.selectAllEnabled();
    }
}
