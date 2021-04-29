<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*"%>
<%
request.setCharacterEncoding("utf-8");
String type = request.getParameter("type");

ConnectDB connectDB = ConnectDB.getInstance();

/*안드로이드 요청1: 가능한 아파트 조회
요청: 1. 전세/월세 2. 대출 제외 예산 3. 지역(법정동) 4. 사람정보 for 대출
응답: 가능한 아파트 json list, 대출 정보 json

1. 전세 : a. 사람 정보 가지고 대출 조회(비상자금, 직장인)-> b. 아파트list 완전 탐색하며 전세자금대출 조회-> c. a,b 통합 대출 가능 아파트 리스트 계산 ->  a,b의 대출 정보, c의 아파트 리스트 안드로이드로 전송 
2. 월세 : a. 사람 정보 가지고 대출 조회(비상자금, 직장인)-> b. 통합 대출 가능 아파트 리스트 계산 ->  a의 대출 정보, b의 아파트 리스트 안드로이드로 전송 

*/
if (type == null) { 
	return;
}else if (type.equals("jeon")) {
	
	
}else if (type.equals("wol")) {
		
	
}else {
	System.out.println("에러입니다");
}
%>