package com.zjh.cms.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.cms.system.domain.Dept;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.mapper.RoleMapper;
import com.zjh.cms.system.mapper.UserMapper;
import com.zjh.cms.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Cacheable(value = "user")
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
//    @CachePut(value = "user",key = "#entity.id")
    public User saveUser(User entity) {
        boolean flag = save(entity);
        return entity;
    }

    @Override
    @CachePut(value = "user",key = "#entity.id")
    public User updateUser(User entity) {
        return updateById(entity)?entity:null;
    }

    @Override
    public boolean removeById(Serializable id) {
        //根据用户id删除用户角色关联关系
        roleMapper.deleteRoleUserByUid(id);
        //删除头像【默认头像不删】
        return super.removeById(id);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        //根据用户ID删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(uid);
        if(null!=ids&&ids.length>0) {
            for (Integer rid : ids) {
                this.roleMapper.insertUserRole(uid,rid);
            }
        }
    }
}
