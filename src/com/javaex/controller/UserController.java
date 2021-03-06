package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
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
		} else if ("login".equals(action)) {
			System.out.println(" user > login 시작 ");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
//			id password 잘 들어오는지 확인
			System.out.println("1차확인 id password "+id + password);
			
			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id, password);
			
//			userVo 가 제대로 오는지 확인
			System.out.println("2차확인 userVo "+authVo);
			
			if(authVo == null) { //로그인실패
				System.out.println(" 3차 로그인 실패 ");
				WebUtil.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
			} else { // 로그인 성공
				System.out.println(" 3차 로그인 성공 ");
				
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authVo);
				
				WebUtil.redirect(request, response, "/mysite/main");
			}
			
//			login 종료
		} else if ("logout".equals(action)) {
			System.out.println("user > logout 실행 ");
			
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
//			세션 지워서 비우기
			
			WebUtil.redirect(request, response, "/mysite/main");
			
//			logout 종료
			
		} else {
			System.out.println("파라미터가 잘못 되었습니다.");

//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
		} // else 종료
		 
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // doGet 종료

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		doGet(request, response);
	} // doPost 종료

}
