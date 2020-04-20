package com.zjh.cms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.cms.system.common.Constant;
import com.zjh.cms.system.common.TreeNode;
import com.zjh.cms.system.common.TreeNodeBuilder;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.mapper.UserMapper;
import com.zjh.cms.system.service.PermissionService;
import org.assertj.core.api.IntegerAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CmsApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Autowired
    private PermissionService permissionService;;
    @Test
    public void test(){
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        //
        wrapper.eq("type", Constant.TYPE_MENU);
        wrapper.eq("available",Constant.AVALIABLE_TRUE);

        List<Permission> list = permissionService.list(wrapper);;
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
        List<TreeNode> build = TreeNodeBuilder.build(treeNodes, 1);
        System.out.println(build);
    }

    @Test
    public void testArrays(){
        Integer[] strArray = new Integer[]{1,2,3};
        List<Integer> a = Arrays.asList(strArray);
        System.out.println(a);
    }
}
