package com.zjh.cms.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zjh.cms.system.domain.Dept;
import com.zjh.cms.system.mapper.DeptMapper;
import com.zjh.cms.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 老雷
 * @since 2020-04-15
 */
@Service
@Transactional
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Override
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean save(Dept entity) {
        return super.save(entity);
    }

    @Override
    public boolean update(Dept entity, Wrapper<Dept> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
}
