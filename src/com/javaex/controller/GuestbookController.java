package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.GuestbookDao;
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestbookController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("/guest 시작");
		
		String action = request.getParameter("action");
		
		if ("addList".equals(action)) {
			System.out.println("user > addlist 시작");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();
			
//			Servlet간 공유하는 게스트북 객체
			request.setAttribute("guestbookList", guestbookList);
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
			
		} else if ("write".equals(action)) {
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
			
			
//			리다이렉트 
			WebUtil.redirect(request, response, "/mysite/guest");
			
//			포워드
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
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
			WebUtil.redirect(request, response, "/mysite/guest");
			
//			delete 종료
		} else if ("modifyForm".equals(action)) {
			System.out.println("user > modifyForm 시작 세션활용 ");
			
//			no 형변환
			int no = Integer.parseInt(request.getParameter("no"));
			
//			숫자로 변경한 no로 대상 식별
			UserVo userVo = new UserDao().getNo(no);
			System.out.println("userVo 출력 "+userVo);
			
//			Action으로 넘어온 값을 변경시킨후 JSP 페이지로 넘겨주기
			request.setAttribute("userVo", userVo);
			
//			포워드
			WebUtil.forward(request, response, "WEB-INF/views/user/modifyForm.jsp");
			
//			modifyForm 종료
		} else if ("modify".equals(action)) {	
			System.out.println("user > modify 시작 ");
			
//			파라미터 4개를 꺼내온다
			String no = request.getParameter("no");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");			

//			no 형변환
			int userNo = Integer.parseInt(request.getParameter("no"));
			
//			vo 만들기
			UserVo userVo = new UserVo(userNo, id, password, name, gender);
			
//			userVo에 잘 담기는지 확인
//			System.out.println(userVo);
			
//			다오를 불러온다
			UserDao userDao = new UserDao();
			
//			다오 속에 쿼리 넣어 수정 처리
			userDao.UserUpdate(userVo);
			
//			세션에 수정한 정보를 넣는다
			HttpSession session = request.getSession();
			
//			UserVo authVo = new UserDao().getUser(id, password);
//			System.out.println("authVo 출력 "+authVo);
			
//			userVo를 그대로 집어넣으니 비번 유출등이 걱정된다 괜찮을까? 다른 방법이 없을까?
			session.setAttribute("authUser", userVo);
			
			
//			리다이렉트 - 포워드 방식 쓰면 에러남
			WebUtil.redirect(request, response, "/mysite/main");
			
//			modify 종료
		} else {
			System.out.println("방명록 리스트");

			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();

//			Servlet간 공유하는 게스트북 객체
			request.setAttribute("guestbookList", guestbookList);
			
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
		}
		
		
		
	} // doGet 종료

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	} // doPost 종료

}
