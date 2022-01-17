package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

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
			query += " 			,user_no uno ";
			query += " 			,ur.id id ";
			query += " 			,ur.password password ";
			query += " 			,ur.name name ";
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
				int uno = rs.getInt("uno");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");

				// 컨텐츠 공백 띄어쓰기 입출력 개선
				content = content.replace(" ", "&nbsp;");
				content = content.replace("\n", "<br>");

				BoardVo boardVo = new BoardVo(bno, title, content, hit, regDate, uno, id, password, name);
				boardList.add(boardVo);

			} // 한줄씩 꺼내서 변수에 담는다

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();

		return boardList;
	} // getList 종료

	public BoardVo readContent(int index) {

		BoardVo boardVo = null;

		try {

			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  bd.no bno ";
			query += " 			,title ";
			query += " 			,content ";
			query += " 			,hit ";
			query += " 			,to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') regDate ";
			query += " 			,user_no uno ";
			query += " 			,ur.id id ";
			query += " 			,ur.password password ";
			query += " 			,ur.name name ";
			query += " from board bd, users ur ";
			query += " where bd.user_no = ur.no ";
			query += " and bd.no = ? ";

			System.out.println(query + " 리스트 작성중");

//			문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

//			바인딩
			pstmt.setInt(1, index);

//			실행 rs = result select
			rs = pstmt.executeQuery();

			// 4.결과처리 next는 자체적으로 불린 값을 가지고 있다 (rs.next() == true)
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("regDate");
				int uno = rs.getInt("uno");
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");

				// 컨텐츠 공백 띄어쓰기 입출력 개선
				content = content.replace(" ", "&nbsp;");
				content = content.replace("\n", "<br>");

				boardVo = new BoardVo(bno, title, content, hit, regDate, uno, id, password, name);

				System.out.println("boardVo 출력 " + boardVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();

		return boardVo;
	} // read 종료

	public void hitCount(int index) {

//		BoardVo hitCount = null;

		try {
			getConnection();

//			hit가 필드가 varchar 타입인 경우 TO_NUMBER로 캐스팅 
//			NOT NULL 허용일 경우 NVL로 NULL값을 0으로 치환 대응 -Tibero

			String query = "";
			query += " UPDATE board ";
			query += " set HIT = NVL(HIT, 0) + 1 ";
			query += " WHERE board.no = ? ";

			System.out.println(query);

//			문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

//			바인딩
			pstmt.setInt(1, index);

//			실행 rs = 쿼리는 업데이트인데 rs 는 데이터 받기처리 
			pstmt.executeUpdate();
//			rs = pstmt.executeQuery();

			// 4.결과처리 next는 자체적으로 불린 값을 가지고 있다 (rs.next() == true)
//			while (rs.next()) {
//				int hit = rs.getInt("hit");

//				hitCount.setHit(hit);
//				System.out.println("boardVo 카운터 " + hitCount);

//			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

//		5.자원 닫기
		close();

	} // hit 종료

	public void deleteContent(int no, int userNo) {

		try {
			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " DELETE FROM board ";
			query += " WHERE board.no = ? ";
			query += " and board.user_no = ? ";

			System.out.println(query + " 글 삭제 처리중");

//			문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

//			바인딩
			pstmt.setInt(1, no);
			pstmt.setInt(2, userNo);

//			실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

//		5.자원 닫기
		close();

	} // deleteContent 종료

	public void insertContent (BoardVo boardVo) {

		try {
			getConnection();

			String query = "";
			query += " INSERT INTO board  ";
			query += " VALUES (seq_board_no.nextval, "; // 식별번호(no시퀀스)
			query += " ?, "; // 제목
			query += " ?, "; // 내용
			query += " 0, "; // 조회수
			query += " sysdate, "; // 등록일
			query += " ?) "; // 작성자번호 uno user_no

			System.out.println(query + " 게시글 추가중");

//		문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

//		바인딩		
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getUno());

			pstmt.executeUpdate(); // 쿼리문 실행

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 5. 자원정리
		close();

	} // insertContent 종료
	
	public void modifyContent(BoardVo boardVo) {

		try {
			getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " UPDATE board ";
			query += " set		title = ? ";
			query += " 			,content = ? ";
			query += " WHERE board.no = ? ";

			System.out.println(query);

//			문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

//			바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getBno());

//			실행
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		
	} // modifyContent 종료
	
	
	
} // The end of BoardDao
