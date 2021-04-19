<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage = "500.jsp"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content" content="text/html;" charset="UTF-8">
	<title>에러 페이지 생성</title>
</head>
<body>
<%
	String name = request.getParameter("name");
	if(name==null) {
		throw new NullPointerException();
	}
%>
</body>
</html>