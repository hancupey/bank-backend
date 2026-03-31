package com.hancu.bankproject.system.controller;

import com.hancu.bankproject.common.api.ApiResponse;
import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.system.domain.User;
import com.hancu.bankproject.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<PageResponse<User>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageResponse<User> result = userService.findAll(page, size);
        return ApiResponse.success(result);
    }

    @GetMapping("/{id}")
    public ApiResponse<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ApiResponse.success(user);
    }

    @PostMapping
    public ApiResponse<User> save(@RequestBody User user) {
        User saved = userService.save(user);
        return ApiResponse.success(saved);
    }

    @PutMapping("/{id}")
    public ApiResponse<User> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User updated = userService.save(user);
        return ApiResponse.success(updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ApiResponse.success();
    }

    @GetMapping("/org/{orgId}")
    public ApiResponse<List<User>> findByOrganizationId(@PathVariable Long orgId) {
        List<User> users = userService.findByOrganizationId(orgId);
        return ApiResponse.success(users);
    }
}
