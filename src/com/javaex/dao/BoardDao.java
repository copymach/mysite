package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.PersonVo;

public class BoardDao {

//	필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "1234";
	
	
//	생성자 컨스트럭터
	public BoardDao() {
	}
	
//	메서드 gs 
	
//	메서드 일반
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
	
	public List<BoardVo> getList() {
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  bd.no bno ";
			query += " 			,title ";
			query += " 			,content ";
			query += " 			,hit ";
			query += " 			,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate ";
			query += " 			,user_no ";
			query += " 			,ur.name uname ";
			query += " 			,user_no ";
			query += " from board bd, users ur ";
			query += " where bd.user_no = ur.no ";
			query += " order by regDate desc ";
			
			System.out.println(query + " 리스트 작성중");

//			문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
//			바인딩은 ? 표시가 없어서 처리 할게 없음		

//			실행 rs = result select
			rs = pstmt.executeQuery();

			// 4.결과처리 next는 자체적으로 불린 값을 가지고 있다

			while (rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regDate");
				String userNo = rs.getString("user_no");
				String uname = rs.getString("uname");
				
				//컨텐츠 공백 띄어쓰기 입출력 개선
				content = content.replace(" ", "&nbsp;");
				content = content.replace("\n", "<br>");

				BoardVo boardVo = new BoardVo(bno, title, content, hit, regDate, userNo, uname);
				boardList.add(boardVo);

			} // 한줄씩 꺼내서 변수에 담는다

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();

		return boardList;
	} // getList 종료
	
	public BoardVo getContent(int index) {

		BoardVo boardVo = new BoardVo();
//		System.out.println("getContent boardVo 출력 "+boardVo);
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  bd.no bno ";
			query += " 			,title ";
			query += " 			,content ";
			query += " 			,hit ";
			query += " 			,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate ";
			query += " 			,user_no ";
			query += " 			,ur.name uname ";
			query += " 			,user_no ";
			query += " from board bd, users ur ";
			query += " where bd.user_no = ur.no ";
			query += " and ur.no = ? ";
			
			System.out.println(query + " 리스트 작성중");

//			문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
//			바인딩
			pstmt.setInt(1, index);

//			실행 rs = result select
			rs = pstmt.executeQuery();

			// 4.결과처리 next는 자체적으로 불린 값을 가지고 있다 (rs.next() == true)
			while (rs.next()) {
				int no = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regDate");
				String userNo = rs.getString("user_no");
				String uname = rs.getString("uname");
				
				//컨텐츠 공백 띄어쓰기 입출력 개선
				content = content.replace(" ", "&nbsp;");
				content = content.replace("\n", "<br>");

//				BoardVo boardVo = new BoardVo(no, title, content, hit, regDate, userNo, uname);
				
				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContent(content);
				boardVo.setHit(hit);
				boardVo.setReg_date(regDate);
				boardVo.setUser_no(userNo);
				boardVo.setUser_name(uname);
				
//				BoardVo bo = new BoardVo(no, title, content, hit, regDate, userNo, uname);
				
			} 

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();

		return boardVo;
	} // getContent 종료
	
	
	public BoardVo hit (int index) {

		BoardVo boardVo = new BoardVo();
//		System.out.println("getContent boardVo 출력 "+boardVo);
		
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " UPDATE board ";
			query += " set HIT = NVL(HIT, 0) + 1 ";
			query += " WHERE board.no = ? ";
	
			System.out.println(query);

//			문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

//			바인딩
			pstmt.setInt(1, index);

//			실행
			rs = pstmt.executeQuery();
		 
			while (rs.next()) {
				int hit = rs.getInt("hid");

				boardVo.setHit(hit);
				
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

//		5.자원 닫기
		close();
		return boardVo;
		
	} // hit 종료
	
	
	
} // The end of BoardDao
