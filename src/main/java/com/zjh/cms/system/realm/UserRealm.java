package com.zjh.cms.system.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjh.cms.system.common.ActiverUser;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * @Author Zhjh
     * @Description 认证
     * @Date 23:17 2020/4/11
     * @Param [principalCollection]
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname", token.getPrincipal().toString());
        User user = userService.getOne(queryWrapper);
        if (null != user) {
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt,
                    this.getName());
            return info;
        }
        return null;
    }
}
