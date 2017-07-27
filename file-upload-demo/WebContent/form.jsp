<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>

<h1>文件上传示例</h1>

<form action="upload" method="post" enctype="multipart/form-data">

	<input type="text" name="uname"> <br/>
	<input type="file" name="file1"> <br/>
	<input type="file" name="file2"> <br/>
	
	<input type="submit" name="上传">
</form>

</body>
</html>