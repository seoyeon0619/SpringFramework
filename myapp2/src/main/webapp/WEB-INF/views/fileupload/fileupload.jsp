<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form  name="myform" method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/fileupload/save">	
	파일첨부 : <input type="file" name="file1" id="file1">
	<input type="submit" value="파일전송"/>
</form>	
</body>
</html>