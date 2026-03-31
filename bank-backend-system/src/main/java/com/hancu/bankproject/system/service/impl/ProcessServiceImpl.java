package com.hancu.bankproject.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.common.exception.BusinessException;
import com.hancu.bankproject.system.domain.Process;
import com.hancu.bankproject.system.mapper.ProcessMapper;
import com.hancu.bankproject.system.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Override
    public PageResponse<Process> findAll(int pageNum, int pageSize) {
        Page<Process> page = new Page<>(pageNum, pageSize);
        IPage<Process> result = processMapper.selectPage(page, null);
        return new PageResponse<>(result.getTotal(), result.getRecords());
    }

    @Override
    public Process findById(Long id) {
        Process process = processMapper.selectById(id);
        if (process == null) {
            throw new BusinessException("流程不存在");
        }
        return process;
    }

    @Override
    @Transactional
    public Process save(Process process) {
        if (process.getName() == null || process.getName().trim().isEmpty()) {
            throw new BusinessException("流程名称不能为空");
        }
        if (process.getId() == null) {
            processMapper.insert(process);
        } else {
            processMapper.updateById(process);
        }
        return process;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (processMapper.selectById(id) == null) {
            throw new BusinessException("流程不存在");
        }
        processMapper.deleteById(id);
    }

    @Override
    public List<Process> findByOrganizationId(Long orgId) {
        return processMapper.selectByOrgId(orgId);
    }

    @Override
    public List<Process> findByType(String type) {
        return processMapper.selectByType(type);
    }

    @Override
    public List<Process> findByStatus(String status) {
        return processMapper.selectByStatus(status);
    }
}
