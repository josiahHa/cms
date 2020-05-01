package com.zjh.cms.system.service;

import com.zjh.cms.system.common.TreeNode;
import com.zjh.cms.system.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老雷
 * @since 2020-04-15
 */
public interface DeptService extends IService<Dept> {

    List<TreeNode> listDeptTreeNode();
}
