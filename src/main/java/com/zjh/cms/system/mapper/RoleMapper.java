package com.zjh.cms.system.mapper;

import com.zjh.cms.system.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 老雷
 * @since 2020-04-18
 */
public interface RoleMapper extends BaseMapper<Role>{

    void deleteRolePermissionByRid(Serializable id);

    void deleteRoleUserByRid(Serializable id);

    List<Integer> queryRolePermissionIdsByRid(Integer roleId);
}
