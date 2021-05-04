package user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.portable.OutputStream;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;




public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {}

	// oracle 계정
	private static final String jdbcUrl = "jdbc:oracle:thin:@3.34.216.87:1521:XE";
	private static final String userId = "system";
	private static final String userPw = "oracle";
	Statement stmt = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;

	String sql = "";
	String sql2 = "";
	
	// 지역코드를 이름으로 테이블을 생성하는 함수
	public void tblCreate(String loc) {	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "create table "+loc+"(hYear CHAR(4), hName VARCHAR2(70) NOT NULL, hFloor VARCHAR2(4), hArea VARCHAR2(10) NOT NULL,addDong VARCHAR2(30) NOT NULL, addJibun VARCHAR2(10) NOT NULL, warFee NUMBER NOT NULL, renFee NUMBER NOT NULL, PointX NUMBER(13,10) NOT NULL, PointY NUMBER(13,10) NOT NULL,hcate NUMBER NOT NULL)";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tblDelete(String tbl) {	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "drop table "+tbl;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// home 정보 추가하는 함수
	public void homeInsert(home home, String loc) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "insert into " +loc+ " values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, home.hYear);
			pstmt.setString(2, home.hName);
			pstmt.setString(3, home.hFloor);
			pstmt.setString(4, home.hArea);
			pstmt.setString(5, home.addDong);
			pstmt.setString(6, home.addJibun);
			pstmt.setInt(7, home.warFee);
			pstmt.setInt(8, home.renFee);
			pstmt.setDouble(9, home.pointX);
			pstmt.setDouble(10, home.pointY);
			pstmt.setInt(11, home.hCate);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 안드로이드 요청2: 모든 환자 정보 */
	public JSONArray bringHomeInfo(String table, String pName,String rName,String company, String comDay, String income,String budget) {
		JSONArray arr = new JSONArray();
		try {
			//월차면 1,2 대출만 가지고 1,2 대출+ budget 보다 작은 렌트비+한달월세 집 출력하
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			
			JSONObject loan1=woori("01",pName,rName,company,comDay,income);
			JSONObject loan2=woori("02",pName,rName,company,comDay,income);
			//전/월세 구
			String rent=table.substring(0,1);
			//비상자금 대출금
			double loanBi=(double) loan1.get("APV_AM");
			
			//직장인 대출
			//System.out.println(loanBi+"%");
			
			double loanJik=(double) loan2.get("APV_AM");
			//System.out.println(loanJik+"^");
			
			double allMoney=0;
			allMoney=Double.parseDouble(budget)+loanBi+loanJik;
			System.out.println("다더한거: "+allMoney+"   int형한"+(int)allMoney);
			

			int money=(int)allMoney/10000;
			System.out.println(money);
			
			money=500000;
			sql = "select * from (select * from "+table+" where warfee<"+money+" order by warfee) where rownum<=10";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
						
			

			if(rs != null){
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				JSONObject all=new JSONObject();
				
				
				obj.put("hyear", rs.getString(1));
				obj.put("hname", rs.getString(2));
				obj.put("hfloor", rs.getString(3));
				obj.put("harea", rs.getString(4));
				obj.put("adddong", rs.getString(5));
				obj.put("addjibun", rs.getString(6));
				String warfee=Integer.toString(rs.getInt(7));
				obj.put("warfee", warfee);
				obj.put("renfee", Integer.toString(rs.getInt(8)));
				obj.put("pointx", Double.toString(rs.getDouble(9)));
				obj.put("pointy", Double.toString(rs.getDouble(10)));
				obj.put("hcate", Integer.toString(rs.getInt(11)));
				
				JSONObject loan3=null;
				if(rent.equals("a")) {loan3=wo03(income,warfee,"table.substring(1,7)");}

				if (obj != null) {
					all.put("home",obj);
					all.put("loan1",loan1);
					all.put("loan2",loan2);
					if(rent.equals("a"))all.put("loan3",loan3);

					arr.add(all);}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arr);
		return arr;
	}
	
	
	
	
	
	
	public JSONObject wo03(String income, String warFee,String addDong) throws IOException, ParseException{
		URL url=new URL("https://openapi.wooribank.com:444/oai/wb/v1/lease/getLeaseHouseLoanAm");
		
		HttpURLConnection conn =(HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("appKey", "l7xxcYoENLGuojyu3d97r4IM2jCuDNEHnS7P");
		
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		String jsonIn="";
		

			jsonIn= "{\n\"dataHeader\": {\n  \"UTZPE_CNCT_IPAD\": \"\",\n  \"UTZPE_CNCT_MCHR_UNQ_ID\": \"\",\n  \"UTZPE_CNCT_TEL_NO_TXT\": \"\",\n  \"UTZPE_CNCT_MCHR_IDF_SRNO\": \"\",\n  \"UTZ_MCHR_OS_DSCD\": \"\",\n  \"UTZ_MCHR_OS_VER_NM\": \"\",\n  \"UTZ_MCHR_MDL_NM\": \"\",\n  \"UTZ_MCHR_APP_VER_NM\": \"\"\n},";
			jsonIn+= "\n\"dataBody\": {\n  \"CRINF_INQ_AGR_YN\": \"Y\",\n  \"PSN_INF_OFR_AGR_YN\": \"Y\",\n  \"PRCI_UTZ_AGR_YN\": \"Y\",\n  \"SLF_ANL_ICM_AM\": \""+income+"\",\n  \"ADR_KDCD\": \"3\",\n  \"POST_SRNO\": \"0\",\n  \"BLD_MNG_NO\": \"1111111111\",\n  \"MDBT_RQ_AM\": \"0\",\n  \"LEAS_GRN_AM\": \""+warFee+"\",\n  \"LAWC_ADDN_NO\": \""+addDong+"\"\n}\n}\n";
	// 신용조회동의여부, 개인정보제공동의여부,  개인신용정보이용동의여부, *본인연간소득금액, 주소종류코드, 우편일련번호, 건물관리번호, 주채무신청금액, *임차보증금액, *법정행정동번호
		
		
		byte[] body=jsonIn.getBytes();
		conn.setFixedLengthStreamingMode(body.length);
        conn.setDoOutput(true);

        java.io.OutputStream out = conn.getOutputStream();
        out.write(body);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
        	line.trim();
        //	System.out.println("%"+line+"%");
          sb.append(line);
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());
        String jsonStr=sb.toString();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonStr);
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject test= (JSONObject) jsonObj.get("dataBody");
        
      //  System.out.println(test);
        return test;
		}
	
	
	public JSONObject woori(String loanType, String pName,String rName,String company, String comDay, String income) throws IOException, ParseException{
		URL url=null;
		if(loanType=="01") {
			url=new URL("https://openapi.wooribank.com:444/oai/wb/v1/credit/getCreditLoanEmFnd");
		}
		else if(loanType=="02") {
			url=new URL("https://openapi.wooribank.com:444/oai/wb/v1/credit/getCreditLoanWon");
		}
		
		HttpURLConnection conn =(HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("appKey", "l7xxcYoENLGuojyu3d97r4IM2jCuDNEHnS7P");
		
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		String jsonIn="";
		
		if(loanType=="01") {
			jsonIn= "{\n\"dataHeader\": {\n  \"UTZPE_CNCT_IPAD\": \"\",\n  \"UTZPE_CNCT_MCHR_UNQ_ID\": \"\",\n  \"UTZPE_CNCT_TEL_NO_TXT\": \"\",\n  \"UTZPE_CNCT_MCHR_IDF_SRNO\": \"\",\n  \"UTZ_MCHR_OS_DSCD\": \"\",\n  \"UTZ_MCHR_OS_VER_NM\": \"\",\n  \"UTZ_MCHR_MDL_NM\": \"\",\n  \"UTZ_MCHR_APP_VER_NM\": \"\"\n},";
			jsonIn+=   "\n\"dataBody\": {\n  \"ENCY_ACNM_NO\": \""+pName+"\",\n  \"CUS_KORL_NM\": \""+rName+"\",\n  \"RQ_AM\": 3000000\n}\n}\n";
			//jsonIn+= "\n\"dataBody\": {\n  \"ENCY_ACNM_NO\": \"PfaC0qTumwYDk8TloqA==\",\n  \"CUS_KORL_NM\": \"홍길동\",\n  \"RQ_AM\": 3000000\n}\n}\n";
		}
		else if(loanType=="02") {
		jsonIn="{\n \"dataHeader\": {\n   \"UTZPE_CNCT_IPAD\": \"\",\n   \"UTZPE_CNCT_MCHR_UNQ_ID\": \"\",\n   \"UTZPE_CNCT_TEL_NO_TXT\": \"\",\n   \"UTZPE_CNCT_MCHR_IDF_SRNO\": \"\",\n   \"UTZ_MCHR_OS_DSCD\": \"\",\n   \"UTZ_MCHR_OS_VER_NM\": \"\",\n   \"UTZ_MCHR_MDL_NM\": \"\",\n   \"UTZ_MCHR_APP_VER_NM\": \"\"\n },";
			jsonIn+= "\n \"dataBody\": {\n   \"ENCY_ACNM_NO\": \""+pName+"\",\n   \"CUS_KORL_NM\": \""+rName+"\",\n   \"RQ_AM\": \"15000000\" ,\n   \"BZPE_CRNO\": \""+company+"\",\n\"ENCN_DT\": \""+comDay+"\",\n\"HLD_YINC_AM\": "+Integer.parseInt(income)+",\n\"ORG_NAME\": \"WOORIBANK\", \"EXTR_IDF_REFC_KEY_TXT\": \"woori202005180303\"\n }\n}\n";
			//jsonIn+= "\n \"dataBody\": {\n   \"ENCY_ACNM_NO\": \"PfaC0qTumwYDk8TloqA==\",\n   \"CUS_KORL_NM\": \"홍길동\",\n   \"RQ_AM\": \"15000000\" ,\n   \"BZPE_CRNO\": \"1002011111\",\n\"ENCN_DT\": \"20200101\",\n\"HLD_YINC_AM\": 50000000,\n\"ORG_NAME\": \"WOORIBANK\", \"EXTR_IDF_REFC_KEY_TXT\": \"woori202005180303\"\n }\n}\n";
		}
		
		
		byte[] body=jsonIn.getBytes();
		conn.setFixedLengthStreamingMode(body.length);
        conn.setDoOutput(true);

        java.io.OutputStream out = conn.getOutputStream();
        out.write(body);
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
        	line.trim();
        //	System.out.println("%"+line+"%");
          sb.append(line);
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());
        String jsonStr=sb.toString();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(jsonStr);
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject test= (JSONObject) jsonObj.get("dataBody");
        
        
      //  System.out.println(test);
        return test;
		}
		
}	
