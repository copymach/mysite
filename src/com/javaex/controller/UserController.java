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
		} else if ("modifyForm".equals(action)) {
			System.out.println("user > modifyForm 시작 회원정보수정 ");
			
//			no 형변환
			int no = Integer.parseInt(request.getParameter("no"));
			
//			숫자로 변경한 no로 대상 식별
			UserVo userVo = new UserDao().getNo(no);
			System.out.println("uservo 출력 "+userVo);
			
//			Action으로 넘어온 값을 변경시킨후 JSP 페이지로 넘겨주기
			request.setAttribute("userVo", userVo);
			
			
//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");

//			modifyForm 종료
		} else if ("modify".equals(action)) {	
			System.out.println("user > modify 시작 ");
			
// 수정폼에서 입력확인 modify?password=22&name=22&gender=female
			
//			파라미터 4개를 꺼내온다
			String no = request.getParameter("no");
//			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");			

//			no 형변환
			int userNo = Integer.parseInt(request.getParameter("no"));
			
//			vo 만들기
			UserVo userVo = new UserVo(userNo, password, name, gender);
			
//			userVo에 잘 담기는지 확인
			System.out.println(userVo);
			
//			다오를 불러온다
			UserDao userDao = new UserDao();
			
//			다오 속에 쿼리 넣어 수정 처리
			userDao.UserUpdate(userVo);
			
			
//			리다이렉트 - 포워드 방식 쓰면 에러남
			WebUtil.redirect(request, response, "/mysite/main");
			
//			modify 종료
		} else {
			System.out.println("파라미터가 잘못 되었습니다.");

//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
			
		} // else 종료
		 
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // doGet 종료

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		doGet(request, response);
	} // doPost 종료

}
