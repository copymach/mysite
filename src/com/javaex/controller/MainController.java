package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
//    	기본생성자
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/main 시작");
		
//		포워드
		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
		

		/*String action = request.getParameter("action");
		
		if ("".equals(action)) {
			System.out.println("main > loginForm 시작");

//			포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");*/
			
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // doGet 종료

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		doGet(request, response);
	} // doPost

}
