<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="org.json.simple.*" %>

<%
ConnectDB connectDB = ConnectDB.getInstance();
ApiExplorer apidb=new ApiExplorer();
ArrayList<CityCode> cityarr=new CityDTO().getCityArr();
//connectDB.woori("01","mon");
//connectDB.woori("02","war");
//connectDB.woori("03","mon");
JSONArray arr=connectDB.bringHomeInfo("b11110");
%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAO확인</title>
 
<%=arr %>

 
</head>
<body>
	<hr>확인됨<hr>
	
</body>
</html>