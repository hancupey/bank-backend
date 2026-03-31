package com.hancu.bankproject.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hancu.bankproject.system.domain.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

    @Select("SELECT * FROM sys_organization WHERE parent_id = #{parentId}")
    List<Organization> selectByParentId(@Param("parentId") Long parentId);

    @Select("SELECT * FROM sys_organization WHERE enabled = 1 ORDER BY level ASC, sort ASC")
    List<Organization> selectAllEnabled();
}
