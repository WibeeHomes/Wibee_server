<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="org.json.simple.*"%>
<%

ApiExplorer apidb=new ApiExplorer();
ArrayList<CityCode> cityarr=new CityDTO().getCityArr();

//이번 연과 달 정보 
String time="202104";

//기존의 테이블이 있는 space drop 후 생성- 기존 정보 전부 삭



//물건지 정보  DB에 저장 (아파트, 연립, 오피스)--> 모든 법정동 코드를 돌며 해당 월의 물건지 정보를 DB에 저한다.
	
for(int i=0;i<cityarr.size();i++){// 법정동 리스트의 크기 만큼 for문 돌기...100은 더미
	apidb.apiDB("apt",cityarr.get(i).getCode(),time);
	apidb.apiDB("offi",cityarr.get(i).getCode(),time);
	apidb.apiDB("rh",cityarr.get(i).getCode(),time);	
}

%>

