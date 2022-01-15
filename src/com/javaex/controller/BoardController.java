package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if ("read".equals(action)) {
			System.out.println(" board > read 시작 ");
			
//			list로부터 게시판 no를 받는다
			int ContentNo = Integer.parseInt(request.getParameter("no"));
			
//			해당 게시물 no로 대상 식별
			
//			카운터			
			new BoardDao().hitCount(ContentNo);
			
//			게시물 불러오기
			BoardVo boardVo = new BoardDao().readContent(ContentNo); 
			System.out.println("boardcontroller > boardVo 출력 "+boardVo);
			
//			Action으로 넘어온 값을 변경시킨후 JSP 페이지로 넘겨주기
			request.setAttribute("bdVo", boardVo);
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		
		} else if ("delete".equals(action)) {
			System.out.println(" board > delete 시작 ");
			
//			user_no하고 게시물 no가 일치하면 삭제
			
//			게시물번호 가져오고
			int no = Integer.parseInt(request.getParameter("bno"));
			int uno = Integer.parseInt(request.getParameter("uno"));
			System.out.println("게시물번호 출력 "+no+uno);
			
			BoardDao boardDao = new BoardDao();
			boardDao.deleteContent(no, uno);
			
			
//			포워드방식 사용시 에러
			WebUtil.redirect(request, response, "/mysite/board");
//			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");			
			
		} else if ("writeForm".equals(action)) {
			System.out.println(" board > writeForm 시작 ");
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
			
		} else if ("write".equals(action)) {
			System.out.println(" board > write 시작 ");
			
			int uno = Integer.parseInt(request.getParameter("uno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			System.out.println("uno 출력 "+uno);
			
			BoardVo boardVo = new BoardVo(uno, title, content);
			
//			insertContent
			
			
		} else if ("modifyForm".equals(action)) {
			
		} else if ("modify".equals(action)) {
			System.out.println(" board > modifyForm 시작 ");
			
		} else if ("dev".equals(action)) {
			System.out.println(" board > modify 시작 ");
			
		} else {
			System.out.println("board 리스트");
			
			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.getList();
			
			System.out.println("보드컨트롤러 boardList 출력 "+boardList);
			
//			Servlet간 공유하는 게시판리스트 객체
			request.setAttribute("bList", boardList);
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
			
		} // else
		
	} // The end of doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	} // The end of doPost

} // The end of BoardController 
