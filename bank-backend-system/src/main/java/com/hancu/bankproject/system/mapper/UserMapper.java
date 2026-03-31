package com.hancu.bankproject.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hancu.bankproject.system.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Select("SELECT COUNT(1) FROM sys_user WHERE username = #{username}")
    boolean existsByUsername(@Param("username") String username);

    @Select("SELECT * FROM sys_user WHERE org_id = #{orgId}")
    List<User> selectByOrgId(@Param("orgId") Long orgId);
}
