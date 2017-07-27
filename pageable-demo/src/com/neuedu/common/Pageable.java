/**
 * 
 */
package com.neuedu.common;

import java.util.List;

/**
 * DESC: 该接口用来实现分页功能
 * 2017年7月27日
 * @author BKF
 */
public interface Pageable<T> {
	/**
	　　* 获得数据结果
	　　* @return
	　　*/
	public List<T> getResult() throws PageException;

	/**
	　　* 获得查询总数
	　　* @return
	　　*/
	public Integer getCount() throws PageException;

	/**
	　　* 获得每页纪录数
	　　* @return
	　　*/
	public Integer getPageSize();

	/**
	　　* 获得当前页编号
	　　* @return
	　　*/
	public Integer getCurrentPage();

	/**
	　　* 获得总页数
	　　* @return
	　　*/
	public Integer getTotalPages() throws PageException;

	/**
	　　* 每页默认显示纪录数
	　　*/
	public final static Integer DEFAULT_PAGESIZE = 20;
}
