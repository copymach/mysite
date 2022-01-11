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
import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
//    	기본생성자
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/user 시작");

//		포워드 내부직원 webutil은 스태틱이라 어디든 쓸 수 있다
//		WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");

		String action = request.getParameter("action");
		
		if ("joinForm".equals(action)) {
			System.out.println("user > joinForm");
//			http://localhost:8088/mysite/user?action=joinForm
			
			
//			UserDao userDao = new UserDao();
//			userDao.insert(null);
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
//			joinForm 종료
		} else if ("join".equals(action)) {
			System.out.println("user > join 시작");

			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(id, password, name, gender);
			
//			유저vo 잘오는지 확인
			System.out.println(userVo);
			
//			유저다오 메모리에 올리고
			UserDao userDao = new UserDao();
			
//			count로 잡아서 가입성공1번에 따라 if문 처리 가능 int count = userDao.insert(userVo);
			userDao.insert(userVo);
			
			
//			join 종료
		} else if ("joinOk".equals(action)) {
			System.out.println("user > joinOk 시작");

		
		} else if ("loginForm".equals(action)) {
			System.out.println("main > loginForm 시작");

//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");

//			loginForm 종료
		} else if ("addList".equals(action)) {
			System.out.println("user > addList 시작");

			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();

//			Servlet간 공유하는 게스트북 객체
			request.setAttribute("gList", guestbookList);
			
			
//			한글 ???으로 출력하는 이상한 명령어
//			PrintWriter out = response.getWriter();
//			out.println("<head>");

//			리다이렉트 처리해야할 것 같은데 생각해보자
//			WebUtil.redirect(request, response, "/mysite/main");

//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
		} else {
			System.out.println("파라미터가 잘못 되었습니다.");
			
		} // else 종료
		 
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // doGet 종료

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		doGet(request, response);
	} // doPost 종료

}
