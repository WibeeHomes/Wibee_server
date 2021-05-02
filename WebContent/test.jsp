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
%>
<%
//apidb.apiDB("apt","11110","202104");
//System.out.println("-----apartment complete-------");
//apidb.apiDB("rh","11110","202104");
//System.out.println("-------office complete-------");
apidb.apiDB("rh","11110","201512");
//System.out.println("-----rh complete------");
//connectDB.tblCreate("home");

//for(int i=3;i<15;i++){
//	System.out.println(cityarr.get(i).getCode());
//	connectDB.tblDelete("a"+cityarr.get(i).getCode());
//	connectDB.tblDelete("b"+cityarr.get(i).getCode());
//}

//connectDB.tblCreate("a"+"11500");
//connectDB.tblCreate("b"+"11500");
//apidb.apiDB("offi","11500","202104");
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