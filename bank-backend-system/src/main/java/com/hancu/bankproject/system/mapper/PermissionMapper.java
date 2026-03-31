package com.hancu.bankproject.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hancu.bankproject.system.domain.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT * FROM sys_permission WHERE code = #{code}")
    Permission selectByCode(@Param("code") String code);

    @Select("SELECT * FROM sys_permission WHERE parent_id = #{parentId}")
    List<Permission> selectByParentId(@Param("parentId") Long parentId);

    @Select("SELECT * FROM sys_permission WHERE enabled = 1 ORDER BY sort ASC")
    List<Permission> selectAllEnabled();
}
