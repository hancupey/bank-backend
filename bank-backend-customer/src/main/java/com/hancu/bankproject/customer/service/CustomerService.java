package com.hancu.bankproject.customer.service;

import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.customer.domain.Customer;
import com.hancu.bankproject.customer.domain.CustomerType;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    Customer getCustomer(Long id);

    PageResponse<Customer> listCustomers(CustomerType type, String nameKeyword, int pageNum, int pageSize);
}
