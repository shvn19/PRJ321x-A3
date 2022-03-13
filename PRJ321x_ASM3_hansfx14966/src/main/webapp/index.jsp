<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>  
<body> 
	
	<% 
	request.setAttribute("action", "home");
	request.getRequestDispatcher("/search").forward(request, response); 
	%>
	
</body>
</html>