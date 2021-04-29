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
	

	/* 안드로이드 요청2: 모든 환자 정보 */
	public JSONArray bringPatientInfo() {
		JSONArray arr = new JSONArray();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "Select * from patient";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			


			if(rs != null){
			while (rs.next()) {
				JSONObject obj = new JSONObject();

				obj.put("pnum", rs.getString(1));
				obj.put("plocnum", rs.getString(2));
				DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
				obj.put("confirmdate", df3.format(rs.getDate(3)));
				if (obj != null)arr.add(obj);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(arr);
		return arr;
	}

}