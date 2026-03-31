package com.hancu.bankproject.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hancu.bankproject.system.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT * FROM sys_role WHERE name = #{name}")
    Role selectByName(@Param("name") String name);

    @Select("SELECT * FROM sys_role WHERE enabled = 1 ORDER BY create_time DESC")
    List<Role> selectAllEnabled();
}
