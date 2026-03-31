package com.hancu.bankproject.system.controller;

import com.hancu.bankproject.common.api.ApiResponse;
import com.hancu.bankproject.system.domain.Role;
import com.hancu.bankproject.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ApiResponse<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();
        return ApiResponse.success(roles);
    }

    @GetMapping("/{id}")
    public ApiResponse<Role> findById(@PathVariable Long id) {
        Role role = roleService.findById(id);
        return ApiResponse.success(role);
    }

    @GetMapping("/name/{name}")
    public ApiResponse<Role> findByName(@PathVariable String name) {
        Role role = roleService.findByName(name);
        return ApiResponse.success(role);
    }

    @PostMapping
    public ApiResponse<Role> save(@RequestBody Role role) {
        Role saved = roleService.save(role);
        return ApiResponse.success(saved);
    }

    @PutMapping("/{id}")
    public ApiResponse<Role> update(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        Role updated = roleService.save(role);
        return ApiResponse.success(updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
        return ApiResponse.success();
    }

    @GetMapping("/enabled")
    public ApiResponse<List<Role>> findAllEnabled() {
        List<Role> roles = roleService.findAllEnabled();
        return ApiResponse.success(roles);
    }
}
