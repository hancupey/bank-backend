package com.hancu.bankproject.customer.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.common.exception.BusinessException;
import com.hancu.bankproject.customer.domain.Customer;
import com.hancu.bankproject.customer.domain.CustomerType;
import com.hancu.bankproject.customer.mapper.CustomerMapper;
import com.hancu.bankproject.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        if (customer.getStatus() == null) {
            customer.setStatus("ACTIVE");
        }
        customerMapper.insert(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existing = customerMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException("客户不存在");
        }
        customer.setId(id);
        customer.setUpdatedAt(LocalDateTime.now());
        customerMapper.updateById(customer);
        return customerMapper.selectById(id);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (customerMapper.selectById(id) == null) {
            throw new BusinessException("客户不存在");
        }
        customerMapper.deleteById(id);
    }

    @Override
    public Customer getCustomer(Long id) {
        Customer customer = customerMapper.selectById(id);
        if (customer == null) {
            throw new BusinessException("客户不存在");
        }
        return customer;
    }

    @Override
    public PageResponse<Customer> listCustomers(CustomerType type, String nameKeyword, int pageNum, int pageSize) {
        Page<Customer> page = new Page<>(pageNum, pageSize);
        IPage<Customer> result;
        if (type != null) {
            result = customerMapper.selectByCustomerType(page, type.name());
        } else if (nameKeyword != null && !nameKeyword.trim().isEmpty()) {
            result = customerMapper.selectByNameContaining(page, nameKeyword.trim());
        } else {
            result = customerMapper.selectPage(page, null);
        }
        return new PageResponse<>(result.getTotal(), result.getRecords());
    }
}
