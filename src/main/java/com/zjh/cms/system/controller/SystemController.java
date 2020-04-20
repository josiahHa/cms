package com.zjh.cms.system.controller;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SystemController {

    /*
     * @Author Zhjh
     * @Description 跳转到登录页
     * @Date 23:46 2020/4/11
     * @Param
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "system/index/login";
    }

    @RequestMapping("index")
    public String index(){
        return "system/index/index";
    }

    @RequestMapping("toDeskManager")
    public String toDeskManager(){
        return "system/index/deskManager";
    }

    @RequestMapping("toLogInfoManager")
    public String toLogInfoManager(){
        return "system/logInfo/logInfoManager";
    }

    @RequestMapping("toNoticeManager")
    public String toNoticeManager(){
        return "system/notice/noticeManager";
    }

    @RequestMapping("toDeptManager")
    public String toDeptManager(){
        return "system/dept/deptManager";
    }
    @RequestMapping("toDeptLeft")
    public String toDeptLeft(){
        return "system/dept/deptLeft";
    }
    @RequestMapping("toDeptRight")
    public String toDeptRight(){
        return "system/dept/deptRight";
    }

    /**
     * 跳转到菜单管理
     *
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转到菜单管理-left
     *
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }


    /**
     * 跳转到菜单管理--right
     *
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }


    /**
     * 跳转到权限管理
     *
     */
    @RequestMapping("toPermissionManager")
    public String toPermissionManager() {
        return "system/permission/permissionManager";
    }

    /**
     * 跳转到权限管理-left
     *
     */
    @RequestMapping("toPermissionLeft")
    public String toPermissionLeft() {
        return "system/permission/permissionLeft";
    }


    /**
     * 跳转到权限管理--right
     *
     */
    @RequestMapping("toPermissionRight")
    public String toPermissionRight() {
        return "system/permission/permissionRight";
    }

    /**
     * 跳转到权限管理--right
     *
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }
}
