package com.zjh.cms.system.service.impl;

import com.zjh.cms.system.domain.Role;
import com.zjh.cms.system.mapper.RoleMapper;
import com.zjh.cms.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 老雷
 * @since 2020-04-18
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public boolean removeById(Serializable id) {
        //根据角色id删除sys_role_permission
        this.getBaseMapper().deleteRolePermissionByRid(id);
        //根据角色id删除sys_role_user
        this.getBaseMapper().deleteRoleUserByRid(id);
        return super.removeById(id);
    }

    @Override
    public List<Integer> queryRolePermissionIdsByRid(Integer roleId) {
//        this.getBaseMapper().select
        return this.getBaseMapper().queryRolePermissionIdsByRid(roleId);
    }
}
