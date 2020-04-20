package com.zjh.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjh.cms.system.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface PermissionMapper extends BaseMapper<Permission> {
    void deleteRolePermissionByPid(@Param("id") Serializable id);
}
