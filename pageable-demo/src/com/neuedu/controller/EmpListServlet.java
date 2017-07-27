/**
 * 
 */
package com.neuedu.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.common.CommonPage;
import com.neuedu.dao.EmployeeDAO;

/**
 * DESC: 
 * 2017年7月27日
 * @author BKF
 */
@WebServlet("/queryEmpList")
public class EmpListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int currentPage = 1;
		try {
			String currPage = req.getParameter("currPage");
			currentPage = Integer.valueOf(currPage);
		} catch (Exception e) { }

		EmployeeDAO dao = new EmployeeDAO();
		CommonPage<Map<String, Object>> emps = dao.queryPage(currentPage);
		
		req.setAttribute("emps", emps);
		req.getRequestDispatcher("emps.jsp").forward(req, resp);
	}
}
