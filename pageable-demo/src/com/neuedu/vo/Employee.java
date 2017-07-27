/**
 * 
 */
package com.neuedu.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * DESC: 
 * 2017年7月27日
 * @author BKF
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer empNo;
	private String ename;
	private String job;
	private Employee mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;

	public Employee() {
	}

	/**
	 * @return the empNo
	 */
	public Integer getEmpNo() {
		return empNo;
	}
	/**
	 * @param empNo the empNo to set
	 */
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}
	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}
	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * @return the mgr
	 */
	public Employee getMgr() {
		return mgr;
	}
	/**
	 * @param mgr the mgr to set
	 */
	public void setMgr(Employee mgr) {
		this.mgr = mgr;
	}
	/**
	 * @return the hiredate
	 */
	public Date getHiredate() {
		return hiredate;
	}
	/**
	 * @param hiredate the hiredate to set
	 */
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	/**
	 * @return the sal
	 */
	public Double getSal() {
		return sal;
	}
	/**
	 * @param sal the sal to set
	 */
	public void setSal(Double sal) {
		this.sal = sal;
	}
	/**
	 * @return the comm
	 */
	public Double getComm() {
		return comm;
	}
	/**
	 * @param comm the comm to set
	 */
	public void setComm(Double comm) {
		this.comm = comm;
	}
	
}
