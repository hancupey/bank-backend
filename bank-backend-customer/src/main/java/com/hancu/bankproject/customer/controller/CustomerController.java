package com.hancu.bankproject.customer.controller;

import com.hancu.bankproject.common.api.ApiResponse;
import com.hancu.bankproject.common.api.PageResponse;
import com.hancu.bankproject.customer.domain.Customer;
import com.hancu.bankproject.customer.domain.CustomerType;
import com.hancu.bankproject.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ApiResponse<Customer> create(@RequestBody Customer customer) {
        return ApiResponse.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ApiResponse<Customer> update(@PathVariable Long id, @RequestBody Customer customer) {
        return ApiResponse.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ApiResponse.ok("删除成功", null);
    }

    @GetMapping("/{id}")
    public ApiResponse<Customer> get(@PathVariable Long id) {
        return ApiResponse.ok(customerService.getCustomer(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<Customer>> list(
            @RequestParam(value = "type", required = false) CustomerType type,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {

        PageResponse<Customer> result =
                customerService.listCustomers(type, name, page, size);
        return ApiResponse.ok(result);
    }
}
