package com.zjh.cms.system.service;

import com.zjh.cms.system.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老雷
 * @since 2020-04-18
 */
public interface RoleService extends IService<Role> {

    List<Integer> queryRolePermissionIdsByRid(Integer roleId);

    void saveRolePermission(Integer rid, Integer[] ids);

    List<Integer> queryUserRoleIdsByUid(Integer id);
}
