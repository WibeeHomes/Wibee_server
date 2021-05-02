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
connectDB.woori();
%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAO확인</title>
 <script src="http://code.jquery.com/jquery-latest.min.js"></script> 

<script>
var data = JSON.stringify({
	  "dataHeader": {
	    "UTZPE_CNCT_IPAD": "",
	    "UTZPE_CNCT_MCHR_UNQ_ID": "",
	    "UTZPE_CNCT_TEL_NO_TXT": "",
	    "UTZPE_CNCT_MCHR_IDF_SRNO": "",
	    "UTZ_MCHR_OS_DSCD": "",
	    "UTZ_MCHR_OS_VER_NM": "",
	    "UTZ_MCHR_MDL_NM": "",
	    "UTZ_MCHR_APP_VER_NM": ""
	  },
	  "dataBody": {
	    "CRINF_INQ_AGR_YN": "Y",
	    "PSN_INF_OFR_AGR_YN": "Y",
	    "PRCI_UTZ_AGR_YN": "Y",
	    "SLF_ANL_ICM_AM": "67000000",
	    "ADR_KDCD": "3",
	    "POST_SRNO": "0",
	    "BLD_MNG_NO": "1111111111",
	    "MDBT_RQ_AM": "0",
	    "LEAS_GRN_AM": "70000000",
	    "LAWC_ADDN_NO": ""
	  }
	});

	var xhr = new XMLHttpRequest();
	xhr.withCredentials = true;

	xhr.addEventListener("readystatechange", function() {
	  if(this.readyState === 4) {
	    console.log(this.responseText);
	  }
	});

	xhr.open("POST", "https://openapi.wooribank.com:444/oai/wb/v1/lease/getLeaseHouseLoanAm");
	xhr.setRequestHeader("appKey", "l7xxr5BZUYuDdfYMDiSLjwxuudN8JcBn1ci9");
	xhr.setRequestHeader("Content-Type", "application/json");

	xhr.send(data);
</script>


 
</head>
<body>
	<hr>확인됨<hr>
	
</body>
</html>