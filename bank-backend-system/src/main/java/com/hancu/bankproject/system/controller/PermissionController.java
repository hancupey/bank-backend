package com.hancu.bankproject.system.controller;

import com.hancu.bankproject.common.api.ApiResponse;
import com.hancu.bankproject.system.domain.Permission;
import com.hancu.bankproject.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ApiResponse<List<Permission>> findAll() {
        List<Permission> permissions = permissionService.findAll();
        return ApiResponse.success(permissions);
    }

    @GetMapping("/{id}")
    public ApiResponse<Permission> findById(@PathVariable Long id) {
        Permission permission = permissionService.findById(id);
        return ApiResponse.success(permission);
    }

    @GetMapping("/code/{code}")
    public ApiResponse<Permission> findByCode(@PathVariable String code) {
        Permission permission = permissionService.findByCode(code);
        return ApiResponse.success(permission);
    }

    @PostMapping
    public ApiResponse<Permission> save(@RequestBody Permission permission) {
        Permission saved = permissionService.save(permission);
        return ApiResponse.success(saved);
    }

    @PutMapping("/{id}")
    public ApiResponse<Permission> update(@PathVariable Long id, @RequestBody Permission permission) {
        permission.setId(id);
        Permission updated = permissionService.save(permission);
        return ApiResponse.success(updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        permissionService.deleteById(id);
        return ApiResponse.success();
    }

    @GetMapping("/enabled")
    public ApiResponse<List<Permission>> findAllEnabled() {
        List<Permission> permissions = permissionService.findAllEnabled();
        return ApiResponse.success(permissions);
    }
}
