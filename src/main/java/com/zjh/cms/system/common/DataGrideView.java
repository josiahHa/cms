package com.zjh.cms.system.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @Author Zhjh
 * @Description Json数据实体
 * @Date 13:04 2020/4/12
 * @Param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGrideView {
    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public DataGrideView(Object data) {
        this.data = data;
    }

    public DataGrideView(Long count, Object data) {
        this.count = count;
        this.data = data;
    }
}
