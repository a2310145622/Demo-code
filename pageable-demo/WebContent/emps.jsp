<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>ENAME</th>
				<th>JOB</th>
				<th>SAL</th>
				<th>COMM</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${requestScope.emps.result}" var="e">
			<tr>
				<td>${e.ENAME}</td>
				<td>${e.JOB}</td>
				<td>${e.SAL}</td>
				<td>${e.COMM}</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<span> 当前第 ${emps.currentPage} 页 / 总共 ${emps.totalPages} 页 </span>
					<a href="queryEmpList?currPage=1">首页</a>

					<a href="queryEmpList?currPage=${emps.currentPage-1}">上一页</a>
					<a href="queryEmpList?currPage=${emps.currentPage+1}">下一页</a>

					<a href="queryEmpList?currPage=${emps.totalPages}">尾页</a>
				</td>
			</tr>
		</tfoot>
	</table>

</body>
</html>