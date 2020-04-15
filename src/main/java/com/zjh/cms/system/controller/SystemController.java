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
}
