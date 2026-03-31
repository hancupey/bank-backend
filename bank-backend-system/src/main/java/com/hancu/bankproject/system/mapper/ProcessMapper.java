package com.hancu.bankproject.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hancu.bankproject.system.domain.Process;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProcessMapper extends BaseMapper<Process> {

    @Select("SELECT * FROM sys_process WHERE org_id = #{orgId}")
    List<Process> selectByOrgId(@Param("orgId") Long orgId);

    @Select("SELECT * FROM sys_process WHERE type = #{type}")
    List<Process> selectByType(@Param("type") String type);

    @Select("SELECT * FROM sys_process WHERE status = #{status}")
    List<Process> selectByStatus(@Param("status") String status);
}
