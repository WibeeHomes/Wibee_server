<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*" %>

<%
ConnectDB connectDB = ConnectDB.getInstance();
ApiExplorer apidb=new ApiExplorer();
%>
<%
//apidb.apiDB("apt","11110","202104");
System.out.println("-----apartment complete-------");
apidb.apiDB("rh","11110","202104");
System.out.println("-------office complete-------");
//apidb.apiDB("rh","11110","201512");
System.out.println("-----rh complete------");
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