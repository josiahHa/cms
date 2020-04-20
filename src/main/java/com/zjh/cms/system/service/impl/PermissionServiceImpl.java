package com.zjh.cms.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.mapper.PermissionMapper;
import com.zjh.cms.system.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PermissionService {

    @Override
    public boolean removeById(Serializable id) {
        //根据删除的权限，删除相对英国的权限与角色相对应的数据
        PermissionMapper permissionMapper = this.getBaseMapper();
        permissionMapper.deleteRolePermissionByPid(id);
        return super.removeById(id);
    }
}
