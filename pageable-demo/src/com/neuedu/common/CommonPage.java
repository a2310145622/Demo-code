/**
 * 
 */
package com.neuedu.common;

import java.math.BigDecimal;
import java.util.List;

import com.neuedu.common.utils.DBUtils;

/**
 * DESC: 
 * 2017年7月27日
 * @author BKF
 */
public class CommonPage<T> implements Pageable<T> {

	private int currentPage;
	private int pageSize;
	private int totalPages;

	protected int count;
	protected List<T> result;

	private String countSql = "";
	private String dataSql = "";

	public CommonPage() {
	}

	/**
	 * 指定当前页
	 * @param currentPage
	 */
	public CommonPage(int currentPage){
		this(currentPage, Pageable.DEFAULT_PAGESIZE);
	}

	/**
	 * 指定当前页和页大小
	 * @param currentPage
	 * @param defaultPagesize
	 */
	public CommonPage(int currentPage, int defaultPagesize) {
		this.currentPage = currentPage;
		this.pageSize = defaultPagesize;
	}

	/**
	 * @return the countSql
	 */
	public String getCountSql() {
		return countSql;
	}

	/**
	 * @param countSql the countSql to set
	 */
	public void setCountSql(String countSql) {
		this.countSql = countSql;
	}

	/**
	 * @return the dataSql
	 */
	public String getDataSql() {
		return dataSql;
	}

	/**
	 * @param dataSql the dataSql to set
	 */
	public void setDataSql(String dataSql) {
		this.dataSql = dataSql;
	}

	protected void checkPage(int currentPage) throws PageException {
		if((currentPage<1) || (currentPage>this.getTotalPages()))
			throw new PageException("页超出范围:总页数为"+this.getTotalPages()+"，当前页为"+currentPage);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getResult() throws PageException {

		if("".equals(dataSql)) {
			throw new PageException("请指定查询数据的SQL");
		} else {
			result = (List<T>)DBUtils.query(dataSql, pageSize, currentPage, pageSize, currentPage);
		}
		return result;
	}

	@Override
	public Integer getCount() throws PageException {
		if("".equals(countSql)) {
			throw new PageException("请指定查询总记录数的SQL");
		} else {
			BigDecimal b = (BigDecimal)DBUtils.querySingleData(countSql);
			count = b.intValue();
		}
		return count;
	}

	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	@Override
	public Integer getCurrentPage() {
		return currentPage;
	}

	@Override
	public Integer getTotalPages() throws PageException {
		if(totalPages==0) 
			this.totalPages=(getCount()+pageSize-1) / pageSize;
		return totalPages;
	}
}
