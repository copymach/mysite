package com.javaex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestbookController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		
		
		
		
		if ("write".equals(action)) {
			System.out.println("user > write 시작");
//			리다이렉트 오류 수정할것

			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			String regDate = request.getParameter("regDate");
			
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content, regDate);
			
//			유저vo 잘오는지 확인
			System.out.println(guestbookVo);
			
//			유저다오 메모리에 올리고
			GuestbookDao guestbookDao = new GuestbookDao();
			
			guestbookDao.ContentInsert(guestbookVo);
			
			
//			리다이렉트 에러
//			WebUtil.redirect(request, response, "/mysite/guest");
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
//			write 종료
		} else if ("deleteForm".equals(action)) {
			System.out.println("user > deleteForm 시작");
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
			
//			deleteForm 종료
		} else if ("delete".equals(action)) {
			System.out.println("guestbook > delete 시작");
			
			
			GuestbookDao guestbookDao = new GuestbookDao();

//			형변환 addList 에서 받아온 no를 숫자로 바꿔준다
			int ContentNo = Integer.parseInt(request.getParameter("no"));

//			deleteForm 에서 가져온 비번 
			String password = request.getParameter("password");

			guestbookDao.ContentDelete(ContentNo, password);

//			리다이렉트
//			WebUtil.redirect(request, response, "/mysite/main");
			WebUtil.redirect(request, response, "/mysite/guest");
			
//			delete 종료
		} else {
			System.out.println("방명록 리스트");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();

//			Servlet간 공유하는 게스트북 객체
			request.setAttribute("gList", guestbookList);
			
//			리다이렉트 
			WebUtil.redirect(request, response, "/mysite/guest");
			
//			포워드
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
		}
		
		
		
	} // doGet 종료

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		doGet(request, response);
	} // doPost 종료

}
