package com.zjh.cms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.cms.system.common.Constant;
import com.zjh.cms.system.common.TreeNode;
import com.zjh.cms.system.common.TreeNodeBuilder;
import com.zjh.cms.system.domain.Dept;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.mapper.UserMapper;
import com.zjh.cms.system.service.PermissionService;
import com.zjh.cms.system.utils.YamlReader;
import com.zjh.cms.system.utils.envsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.File;
import java.io.IOException;
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
        wrapper.eq("available",Constant.AVAILABLE_TRUE);

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
    @Test
    public void testYamlReader(){//测试yaml读取
        String serverHost = (String) YamlReader.instance.getValueByKey("spring.profiles.active");
        System.out.println(serverHost);
    }
    @Test
    public void testfs() throws Exception  {
        String className = "com.zjh.cms.evns.dev.config";
        String methodName = "printMethod";
        Class<Object> clz = (Class<Object>) Class.forName(className);
        //
        Object obj = clz.newInstance();


//        //获取方法
//        Method m = obj.getClass().getDeclaredMethod(methodName);
//        //调用方法
//        List<Object> param = new ArrayList<>();
//        String  result = (String) m.invoke(obj);
//        System.out.println(result);

        //用hutool的方式
//        Object aaa = ReflectUtil.newInstance(clz);
//        ReflectUtil.invoke(aaa, "printMethod");

        envsUtil.call("config","printMethod","asdfasdfsfsdfds");
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void testRedis(){
//        String mmm = stringRedisTemplate.opsForValue().get("mmm");
//        System.out.println(mmm);
//        ListOperations<String,Object> lo = redisTemplate.opsForList();
//        List<TreeNode> mmm = (List<TreeNode>) lo.index("deptTreeNodes::SimpleKey []",0);
        List<TreeNode> mmm = (List<TreeNode>) redisTemplate.opsForValue().get("deptTreeNodes::SimpleKey []");
        for (TreeNode node:mmm) {
            System.out.println(node.getTitle());
        }
        System.out.println(mmm);
    }

    @Test
    public void testRedisObj(){
//        String mmm = stringRedisTemplate.opsForValue().get("mmm");
//        System.out.println(mmm);

//        ListOperations<Object, Object> lo = redisTemplate.opsForList();
//        List<TreeNode> mmm = (List<TreeNode>) lo.index("deptTreeNodes::SimpleKey []",0);
        List<Dept>  o= (List<Dept>) redisTemplate.opsForValue().get("deptTreeNodes::SimpleKey []");
//        User o = (User) redisTemplate.opsForValue().get("user::2");
//        List<Dept> list = JSON.toJavaObject(json, List.class);
        System.out.println(o.toString());
    }

    @Test
    public void testRuntime() throws IOException {
//        Runtime.getRuntime().exec("F:\\learning\\前端\\树列表\\layui-treetable");
        java.awt.Desktop.getDesktop().open(new File("F:\\learning\\前端\\树列表\\layui-treetable"));
    }
}
