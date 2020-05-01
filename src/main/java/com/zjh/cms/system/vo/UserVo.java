package com.zjh.cms.system.vo;

import com.zjh.cms.system.domain.Dept;
import com.zjh.cms.system.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {
    private static final long serialVersionUID=1L;

    private Integer page=1;
    private Integer limit=10;
}
