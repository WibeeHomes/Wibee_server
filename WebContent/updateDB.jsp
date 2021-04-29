<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*"%>
<%
request.setCharacterEncoding("utf-8");
String type = request.getParameter("type");

ConnectDB connectDB = ConnectDB.getInstance();


if (type == null) { 
	return;
}else if (type.equals("bring")) {
	

}else {
	System.out.println("에러입니다");
}
%>