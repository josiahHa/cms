package com.zjh.cms.system.controller;

import com.zjh.cms.system.common.ActiverUser;
import com.zjh.cms.system.common.ResultObj;
import com.zjh.cms.system.common.WebUtils;
import com.zjh.cms.system.domain.LogInfo;
import com.zjh.cms.system.service.LogInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LogInfoService logInfoService;

    @RequestMapping("login")
    public ResultObj login(String loginname, String pwd){
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(loginname,pwd);
        try{
            subject.login(token);
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user",activerUser.getUser());

            //记录登录日志
            LogInfo entity = new LogInfo();
            entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
            entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
            entity.setLogintime(new Date());
            logInfoService.save(entity);

            return ResultObj.LOGIN_SUCCESS;
        }catch (AuthenticationException e){
            return ResultObj.LOGIN_ERROR_PASS;
        }

    }
}
