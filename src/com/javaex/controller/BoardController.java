package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if ("writeForm".equals(action)) {
			System.out.println(" board > writeForm 시작 ");
			
		} else if ("write".equals(action)) {
			System.out.println(" board > write 시작 ");
			
		} else if ("delete".equals(action)) {
			System.out.println(" board > delete 시작 ");
			
		} else if ("read".equals(action)) {
			System.out.println(" board > read 시작 ");
			
//			list로부터 게시판 no를 받는다
			int ContentNo = Integer.parseInt(request.getParameter("no"));
			
//			해당 게시물 no로 대상 식별
			BoardVo boardVo = new BoardDao().getContent(ContentNo); 
			System.out.println("boardcontroller > boardVo 출력 "+boardVo);
			
//			Action으로 넘어온 값을 변경시킨후 JSP 페이지로 넘겨주기
			request.setAttribute("bdVo", boardVo);
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
			
			
//			no를 잘 받아온다 read 메서드
			
		} else if ("modifyForm".equals(action)) {
			System.out.println(" board > modifyForm 시작 ");
			
		} else if ("modify".equals(action)) {
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