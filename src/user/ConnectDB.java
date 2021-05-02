package user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
			sql = "create table "+loc+"(hYear CHAR(4), hName VARCHAR2(70) NOT NULL, hFloor VARCHAR2(4), hArea VARCHAR2(10) NOT NULL,addDong VARCHAR2(20) NOT NULL, addJibun VARCHAR2(10) NOT NULL, warFee NUMBER NOT NULL, renFee NUMBER NOT NULL, PointX NUMBER(13,10) NOT NULL, PointY NUMBER(13,10) NOT NULL,hcate NUMBER NOT NULL)";
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
	public JSONArray bringHomeInfo(String table) {
		JSONArray arr = new JSONArray();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "select * from (select * from "+table+" order by warfee) where rownum<=30";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs != null){
			while (rs.next()) {
				JSONObject obj = new JSONObject();

				obj.put("hyear", rs.getString(1));
				obj.put("hname", rs.getString(2));
				obj.put("hfloor", rs.getString(3));
				obj.put("harea", rs.getString(4));
				obj.put("adddong", rs.getString(5));
				obj.put("addjibun", rs.getString(6));
				obj.put("warfee", Integer.toString(rs.getInt(7)));
				obj.put("renfee", Integer.toString(rs.getInt(8)));
				obj.put("pointx", Double.toString(rs.getDouble(9)));
				obj.put("pointy", Double.toString(rs.getDouble(10)));
				obj.put("hcate", Integer.toString(rs.getInt(11)));

				
				if (obj != null)arr.add(obj);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println(arr);
		return arr;
	}

}