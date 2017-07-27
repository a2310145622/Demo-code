/**
 * 
 */
package com.neuedu.common;

/**
 * DESC: 分页自定义异常
 * 2017年7月27日
 * @author BKF
 */
public class PageException extends Exception {

	private static final long serialVersionUID = 1L;

	public PageException() {
		super();
	}

	public PageException(String msg) {
		super(msg);
	}

}
