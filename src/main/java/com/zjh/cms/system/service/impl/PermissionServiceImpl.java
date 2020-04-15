package com.zjh.cms.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.mapper.PermissionMapper;
import com.zjh.cms.system.service.PermissionService;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper,Permission> implements PermissionService {
}
