package com.zjh.cms.bus.vo;


import com.zjh.cms.bus.domain.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends Customer {

	/*
	 *  序列化
	 */
	private static final long serialVersionUID = 1L;

	private Integer page = 1;
	private Integer limit = 10;

	private Integer[] ids;

}
