package com.zjh.cms.system.vo;

import com.zjh.cms.system.domain.LogInfo;
import com.zjh.cms.system.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class LogInfoVo extends LogInfo {
    private static final long serialVersionUID=1L;

    private Integer page=1;
    private Integer limit=10;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    private Integer[] ids;
}
