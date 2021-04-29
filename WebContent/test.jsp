<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*" %>

<%
ConnectDB connectDB = ConnectDB.getInstance();
ApiExplorer appii=new ApiExplorer();
%>
<%
	request.setCharacterEncoding("UTF-8");
//appii.apiPrint("apartment");
//System.out.println("-----아파트 -------");
//appii.apiPrint("office");
System.out.println("-------쥬택 시작-----");
appii.apiPrint("alliance");
System.out.println("-------주택 끝-----");
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAO확인</title>
</head>
<body>
	<hr>확인됨<hr>

	
	<%
	//JSONArray jarr=connectDB.bringPatientInfo();
	
	%>
	
	
	
</body>
</html>