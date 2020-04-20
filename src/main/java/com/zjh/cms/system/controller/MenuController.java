package com.zjh.cms.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.cms.system.common.*;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.service.PermissionService;
import com.zjh.cms.system.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老雷
 * @since 2019-09-21
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;;

    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(PermissionVo permissionVo){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("type", Constant.TYPE_MENU);
        wrapper.eq("available",Constant.AVALIABLE_TRUE);

        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if(Constant.USER_TYPE_SUPER==user.getType()){
            list = permissionService.list(wrapper);
        }else{
            //根据角色ID+角色+权限
            list = permissionService.list(wrapper);
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for(Permission permission:list){
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String icon = permission.getIcon();
            String href = permission.getHref();
            Boolean spread = permission.getOpen()==Constant.OPEN_TRUE?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }
        treeNodes = TreeNodeBuilder.build(treeNodes,1);

        HashMap<String,List<TreeNode>> map = new HashMap<>();
        map.put("contentManagement",treeNodes);
        return new DataGridView(map);
    }


    /****************菜单管理开始****************/

    /**
     * 加载菜单管理左边的菜单树的json
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo) {
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", Constant.TYPE_MENU);
        List<Permission> list = this.permissionService.list(queryWrapper);
        List<TreeNode> treeNodes=new ArrayList<>();
        for (Permission menu : list) {
            Boolean spread=menu.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu.getTitle(), spread));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * 查询
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo) {
        IPage<Permission> page=new Page<>(permissionVo.getPage(), permissionVo.getLimit());
        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", Constant.TYPE_MENU);//只能查询菜单
        queryWrapper.and(Wrapper -> Wrapper.eq(permissionVo.getId()!=null, "id", permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid", permissionVo.getId()));
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()), "title", permissionVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        this.permissionService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 加载最大的排序码
//     * @param permissionVo
     * @return
     */
    @RequestMapping("loadMenuMaxOrderNum")
    public Map<String,Object> loadMenuMaxOrderNum(){
        Map<String, Object> map=new HashMap<String, Object>();

        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page=new Page<>(1, 1);
        List<Permission> list = this.permissionService.page(page, queryWrapper).getRecords();
        if(list.size()>0) {
            map.put("value", list.get(0).getOrdernum()+1);
        }else {
            map.put("value", 1);
        }
        return map;
    }


    /**
     * 添加
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(PermissionVo permissionVo) {
        try {
            permissionVo.setType(Constant.TYPE_MENU);//设置添加类型
            this.permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 修改
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 查询当前的ID的菜单有没有子菜单
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String,Object> checkMenuHasChildrenNode(PermissionVo permissionVo){
        Map<String, Object> map=new HashMap<String, Object>();

        QueryWrapper<Permission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid", permissionVo.getId());
        List<Permission> list = this.permissionService.list(queryWrapper);
        if(list.size()>0) {
            map.put("value", true);
        }else {
            map.put("value", false);
        }
        return map;
    }

    /**
     * 删除
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo) {
        try {
            this.permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /****************菜单管理结束****************/
}
