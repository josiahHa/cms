package com.zjh.cms.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.cms.system.common.DataGridView;
import com.zjh.cms.system.common.ResultObj;
import com.zjh.cms.system.common.WebUtils;
import com.zjh.cms.system.domain.Notice;
import com.zjh.cms.system.domain.User;
import com.zjh.cms.system.service.NoticeService;
import com.zjh.cms.system.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老雷
 * @since 2020-04-13
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping("loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo){
        IPage<Notice> page = new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        queryWrapper.like(StringUtils.isNotBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        queryWrapper.ge(noticeVo.getStartDate()!=null,"createtime",noticeVo.getStartDate());
        queryWrapper.le(noticeVo.getEndDate()!=null,"createtime",noticeVo.getEndDate());
        queryWrapper.orderByDesc("createtime");
        this.noticeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("addNotice")
    public ResultObj addNotice(NoticeVo noticeVo){
        try{
            noticeVo.setCreatetime(new Date());
            User user = (User) WebUtils.getSession().getAttribute("user");
            noticeVo.setOpername(user.getName());
            this.noticeService.save(noticeVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo){
        try{
            this.noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }

    @RequestMapping("deleteNotice")
    public ResultObj deleteLogInfo(Integer id){
        try{
            this.noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("batchDeleteNotice")
    public ResultObj batchDeleteLogInfo(NoticeVo noticeVo){
        try{
            this.noticeService.removeByIds(Arrays.asList(noticeVo.getIds()));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }
}

