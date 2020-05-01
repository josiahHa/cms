package com.zjh.cms.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjh.cms.system.domain.User;

public interface UserService extends IService<User> {

    User saveUser(User entity);

    User updateUser(User entity);

    void saveUserRole(Integer uid, Integer[] ids);


}
