/**
 * 
 */
package com.neuedu.dao;

import java.util.Map;

import com.neuedu.common.CommonPage;

/**
 * DESC: 
 * 2017年7月27日
 * @author BKF
 */
public class EmployeeDAO {

	/**
	 * 获取分页员工信息
	 * @param currPage
	 * @return
	 */
	public CommonPage<Map<String, Object>> queryPage(int currentPage) {
		
		CommonPage<Map<String, Object>> data = new CommonPage<>(currentPage, 4);

		String countSql = "SELECT count(1) FROM emp WHERE 1=1";
		String dataSql = "SELECT * " +
						 "  FROM (SELECT ROWNUM rn, e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm FROM emp e " +
						 "         WHERE ROWNUM <= ? * ?) t" +
						 " WHERE rn > ? * (?-1)";

		data.setCountSql(countSql);
		data.setDataSql(dataSql);
		return data;
	}
	
	
}
