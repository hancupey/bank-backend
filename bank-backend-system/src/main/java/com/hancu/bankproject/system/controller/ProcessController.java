package com.hancu.bankproject.system.controller;

import com.hancu.bankproject.common.api.ApiResponse;
import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.system.domain.Process;
import com.hancu.bankproject.system.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system/processes")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @GetMapping
    public ApiResponse<PageResponse<Process>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageResponse<Process> result = processService.findAll(page, size);
        return ApiResponse.success(result);
    }

    @GetMapping("/{id}")
    public ApiResponse<Process> findById(@PathVariable Long id) {
        Process process = processService.findById(id);
        return ApiResponse.success(process);
    }

    @PostMapping
    public ApiResponse<Process> save(@RequestBody Process process) {
        Process saved = processService.save(process);
        return ApiResponse.success(saved);
    }

    @PutMapping("/{id}")
    public ApiResponse<Process> update(@PathVariable Long id, @RequestBody Process process) {
        process.setId(id);
        Process updated = processService.save(process);
        return ApiResponse.success(updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteById(@PathVariable Long id) {
        processService.deleteById(id);
        return ApiResponse.success();
    }

    @GetMapping("/org/{orgId}")
    public ApiResponse<List<Process>> findByOrganizationId(@PathVariable Long orgId) {
        List<Process> processes = processService.findByOrganizationId(orgId);
        return ApiResponse.success(processes);
    }

    @GetMapping("/type/{type}")
    public ApiResponse<List<Process>> findByType(@PathVariable String type) {
        List<Process> processes = processService.findByType(type);
        return ApiResponse.success(processes);
    }

    @GetMapping("/status/{status}")
    public ApiResponse<List<Process>> findByStatus(@PathVariable String status) {
        List<Process> processes = processService.findByStatus(status);
        return ApiResponse.success(processes);
    }
}
