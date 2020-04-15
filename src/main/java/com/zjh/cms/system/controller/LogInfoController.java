package com.zjh.cms.system.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.cms.system.common.DataGrideView;
import com.zjh.cms.system.common.ResultObj;
import com.zjh.cms.system.domain.LogInfo;
import com.zjh.cms.system.service.LogInfoService;
import com.zjh.cms.system.vo.LogInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老雷
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/logInfo")
public class LogInfoController {
    @Autowired
    private LogInfoService logInfoService;

    @RequestMapping("loadAllLogInfo")
    public DataGrideView loadAllLogInfo(LogInfoVo logInfoVo){
        IPage<LogInfo> page = new Page<>(logInfoVo.getPage(),logInfoVo.getLimit());
        QueryWrapper<LogInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(logInfoVo.getLoginname()),"loginname",logInfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(logInfoVo.getLoginip()),"loginip",logInfoVo.getLoginip());
        queryWrapper.ge(logInfoVo.getStartDate()!=null,"logintime",logInfoVo.getStartDate());
        queryWrapper.le(logInfoVo.getEndDate()!=null,"logintime",logInfoVo.getEndDate());
        queryWrapper.orderByDesc("logintime");
        this.logInfoService.page(page,queryWrapper);
        return new DataGrideView(page.getTotal(),page.getRecords());
    }

    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(Integer id){
        try{
            this.logInfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("batchDeleteLogInfo")
    public ResultObj batchDeleteLogInfo(LogInfoVo logInfoVo){
        try{
            this.logInfoService.removeByIds(Arrays.asList(logInfoVo.getIds()));
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            return ResultObj.DELETE_ERROR;
        }
    }

}

