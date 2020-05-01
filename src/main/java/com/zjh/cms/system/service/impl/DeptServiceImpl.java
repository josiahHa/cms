package com.zjh.cms.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zjh.cms.system.common.TreeNode;
import com.zjh.cms.system.domain.Dept;
import com.zjh.cms.system.mapper.DeptMapper;
import com.zjh.cms.system.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Cacheable(value = {"dept"})
    public Dept getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    @Cacheable(value = {"deptList"})
    public List<Dept> list() {
        return super.list();
    }

    @Override
    @CachePut(value = "dept",key = "#entity.id")
    public boolean save(Dept entity) {
        return super.save(entity);
    }

    @Override
    @CachePut(value = "dept",key = "#entity.id")
    public boolean update(Dept entity, Wrapper<Dept> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @Override
    @CacheEvict(value = "emp",key = "#id")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    @Cacheable(cacheNames="deptTreeNodes")
    public List<TreeNode> listDeptTreeNode() {
        List<Dept> list = this.list();
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Dept dept: list){
            Boolean spred = dept.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(dept.getId(),dept.getPid(),dept.getTitle(),spred));
        }
        return treeNodes;
    }
}
