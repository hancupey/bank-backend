package com.hancu.bankproject.system.controller;

import com.hancu.bankproject.common.api.ApiResponse;
import com.hancu.bankproject.system.domain.Organization;
import com.hancu.bankproject.system.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ApiResponse<List<Organization>> findAll() {
        List<Organization> organizations = organizationService.findAll();
        return ApiResponse.success(organizations);
    }

    @GetMapping("/{id}")
    public ApiResponse<Organization> findById(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);
        return ApiResponse.success(organization);
    }

    @PostMapping
    public ApiResponse<Organization> save(@RequestBody Organization organization) {
        Organization saved = organizationService.save(organization);
        return ApiResponse.success(saved);
    }

    @PutMapping("/{id}")
    public ApiResponse<Organization> update(@PathVariable Long id, @RequestBody Organization organization) {
        organization.setId(id);
        Organization updated = organizationService.save(organization);
        return ApiResponse.success(updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        organizationService.deleteById(id);
        return ApiResponse.success();
    }

    @GetMapping("/parent/{parentId}")
    public ApiResponse<List<Organization>> findByParentId(@PathVariable Long parentId) {
        List<Organization> organizations = organizationService.findByParentId(parentId);
        return ApiResponse.success(organizations);
    }

    @GetMapping("/enabled")
    public ApiResponse<List<Organization>> findAllEnabled() {
        List<Organization> organizations = organizationService.findAllEnabled();
        return ApiResponse.success(organizations);
    }
}
