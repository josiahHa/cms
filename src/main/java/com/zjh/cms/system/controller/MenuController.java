package com.zjh.cms.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.cms.system.common.*;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.service.PermissionService;
import com.zjh.cms.system.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public DataGrideView loadIndexLeftMenuJson(PermissionVo permissionVo){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("type", Constant.TYPT_MENU);
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
        return new DataGrideView(map);
    }

}
