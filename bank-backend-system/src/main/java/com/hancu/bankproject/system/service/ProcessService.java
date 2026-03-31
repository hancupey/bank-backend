package com.hancu.bankproject.system.service;

import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.system.domain.Process;

import java.util.List;

public interface ProcessService {

    PageResponse<Process> findAll(int pageNum, int pageSize);

    Process findById(Long id);

    Process save(Process process);

    void deleteById(Long id);

    List<Process> findByOrganizationId(Long orgId);

    List<Process> findByType(String type);

    List<Process> findByStatus(String status);
}
