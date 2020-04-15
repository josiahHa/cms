package com.zjh.cms.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.mapper.UserMapper;
import com.zjh.cms.system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
