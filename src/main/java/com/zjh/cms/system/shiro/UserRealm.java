package com.zjh.cms.system.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.cms.system.common.ActiverUser;
import com.zjh.cms.system.common.Constant;
import com.zjh.cms.system.domain.Permission;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.service.PermissionService;
import com.zjh.cms.system.service.RoleService;
import com.zjh.cms.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    @Lazy  //只有使用的时候才会加载
    private UserService userService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //用户登录次数计数  redisKey 前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());

        //通过用户名获得用户信息
        User user = this.rtnUser(name);

        verifyPassword(user,name,password);

        //获得principal
        ActiverUser activerUser = this.rtnActiverUser(user);

        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt,
                this.getName());
        return info;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        ActiverUser activerUser=(ActiverUser) principals.getPrimaryPrincipal();
        User user=activerUser.getUser();
        List<String> permissions = activerUser.getPermissions();
        if(user.getType()==Constant.USER_TYPE_SUPER) {
            authorizationInfo.addStringPermission("*:*");
        }else {
            if(null!=permissions&&permissions.size()>0) {
                authorizationInfo.addStringPermissions(permissions);
            }
        }
        return authorizationInfo;
    }

    private User rtnUser(String name){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", name);
        User user = userService.getOne(queryWrapper);
        return user;
    }

    //验证密码
    private void verifyPassword(User user,String name,String password){
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
        //计数大于5时，设置用户被锁定一小时
        String count_num = opsForValue.get(SHIRO_LOGIN_COUNT + name);
        if(Integer.parseInt(count_num)>=5){
            opsForValue.set(SHIRO_IS_LOCK+name, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK+name, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+name))){
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }

        if(null == user){
            throw new DisabledAccountException("用户名或密码不正确！");
        }
        String salt = user.getSalt();
        password = new Md5Hash(password, salt, 2).toString();
        if(StringUtils.isNotEmpty(password) && !password.equals(user.getPwd())){
            throw new DisabledAccountException("用户名或密码不正确！");
        }else{
            opsForValue.set(SHIRO_LOGIN_COUNT+name, "0");
        }
    }

    //通过user获取Activer,填充用户，角色，权限
    private ActiverUser rtnActiverUser(User user){
        ActiverUser activerUser = new ActiverUser();
        activerUser.setUser(user);
        //根据用户ID查询percode
        //查询所有菜单
        QueryWrapper<Permission> qw=new QueryWrapper<>();
        //设置只能查询菜单
        qw.eq("type",Constant.TYPE_PERMISSION);
        qw.eq("available", Constant.AVAILABLE_TRUE);

        //根据用户ID+角色+权限去查询
        Integer userId=user.getId();
        //根据用户ID查询角色
        List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
        //根据角色ID取到权限和菜单ID
        Set<Integer> pids=new HashSet<>();
        for (Integer rid : currentUserRoleIds) {
            List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
            pids.addAll(permissionIds);
        }
        List<Permission> list=new ArrayList<>();
        //根据角色ID查询权限
        if(pids.size()>0) {
            qw.in("id", pids);
            list=permissionService.list(qw);
        }
        List<String> percodes=new ArrayList<>();
        for (Permission permission : list) {
            percodes.add(permission.getPercode());
        }
        //放到
        activerUser.setPermissions(percodes);
        return activerUser;
    }
}
