package com.chuanqi56.logistics.entity;

import java.io.Serializable;

/**
 * 
 * @Description: 解析JSON字符串返回的结果
 * @author lizhengbo95
 * @date 2015-9-14 下午6:22:11 
 *
 */
public class JsonResult<T> implements Serializable {
	private static final long serialVersionUID = 2845270580836670606L;
	public int ret;
	public String msg;
	public T data;

}
