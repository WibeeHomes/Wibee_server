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
	private static final String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static final String userId = "system";
	private static final String userPw = "oracle";
	Statement stmt = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;

	String sql = "";
	String sql2 = "";
	
	// 지역코드 관련 테이블 생성하는 함수 
	//1. home 정보 추가하는 함수
	public void homeInsert(home home) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "insert into pmoving values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
	/*		pstmt.setString(1, );
			pstmt.setString(2, plocnum);
			pstmt.setDate(3, java.sql.Date.valueOf(visitdate));
			pstmt.setDouble(4, PointX);
			pstmt.setDouble(5, PointY);
			pstmt.setNString(6, address);*/
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}