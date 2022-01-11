package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";
	
//생성자
	public UserDao() {
	}
	
//메서드gs
	
//메서드일반
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	} // getConn 종료

	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	} // close 종료
	
//메서드 저장메서드 (회원가입)
	public int insert(UserVo userVo) {
		
		int count = 0;
				
		getConnection();
		
		try {
//			sql 문자열
			String query = "";
			query += " INSERT INTO users VALUES ( ";
			query += " 			seq_users_no.nextval "; //no
			query += " 			, ? "; //id ?넣을때 따옴표 넣지않기 '?'
			query += " 			, ? "; //password
			query += " 			, ? "; //name
			query += " 			, ? "; //gender
			query += " ) ";
			
//			sql 쿼리
			pstmt = conn.prepareStatement(query);
			
//			바인딩
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());  
			pstmt.setString(4, userVo.getGender());
			
//			쿼리문 실행
			count = pstmt.executeUpdate();
//			rs = pstmt.executeQuery(); 이걸로도 되는데 처리 건수 확인해보려고 executeUpdate 사용 
			
//			결과처리
			System.out.println(count + "건이 처리되었습니다 UserDao");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();
		
//		리턴은 맨 끝자리에 위치
		return count;
		
	} // insert 종료
	
	
	
}

