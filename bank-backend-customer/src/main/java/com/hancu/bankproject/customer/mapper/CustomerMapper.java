package com.hancu.bankproject.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hancu.bankproject.customer.domain.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select("SELECT * FROM customer WHERE customer_type = #{customerType}")
    IPage<Customer> selectByCustomerType(Page<Customer> page, @Param("customerType") String customerType);

    @Select("SELECT * FROM customer WHERE name LIKE CONCAT('%', #{name}, '%')")
    IPage<Customer> selectByNameContaining(Page<Customer> page, @Param("name") String name);
}
