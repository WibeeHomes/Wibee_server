<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="org.json.simple.*" %>

<%
ConnectDB connectDB = ConnectDB.getInstance();
//ApiExplorer apidb=new ApiExplorer();
//ArrayList<CityCode> cityarr=new CityDTO().getCityArr();
//connectDB.woori("01","PfaC0qTumwYDk8TloqA==","홍길동","","","","");
connectDB.woori("03","PfaC0qTumwYDk8TloqA==","홍길동","1002011111","20200101","5000000","6000");
//connectDB.woori("02");
//connectDB.woori("03");
//JSONArray arr=connectDB.bringHomeInfo("b11110");
%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAO확인</title>

</head>
<body>
	<hr>확인됨<hr>
	
</body>
</html>