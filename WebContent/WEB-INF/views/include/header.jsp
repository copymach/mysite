<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.UserVo"%>
<!-- el jstl 구동하기 위한 코드 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
//로그인
UserVo authUser = (UserVo) session.getAttribute("authUser");

//세션을 이용한 방법 - 이름에 적용하면 왜 에러가 날까
//UserVo userInfo = (UserVo)session.getAttribute("userInfo");

//System.out.println(userInfo.toString());
%>


<div id="header" class="clearfix">
	<h1>
		<a href="http://localhost:8088/mysite/main">MySite</a>
	</h1>
	
	<!-- choose 사이에 주석 달면 에러 주의. -->
	
	<c:choose>
		<c:when test="${empty sessionScope.authUser}">
			<!-- 세션 영역에 값이 없으면 로그인 실패, 로그인 전에 사용 -->
			<ul>
				<li><a href="/mysite/user?action=loginForm" class="btn_s">로그인</a></li>
				<li><a href="/mysite/user?action=joinForm" class="btn_s">회원가입</a></li>
			</ul>
		</c:when>
		
		<c:otherwise>
			<!-- 로그인 성공시 출력 -->
			<ul>
				<!-- 세션에서 갱신한 이름을 가져와보자 -->
				<li>${sessionScope.authUser.name} 님 하이 </li> 
				<!-- sessionScope.authUser.name 에서 sessionScope 생략가능 -->
				<li><%=authUser.getName()%> 님 안녕하세요^^</li>
				<li><a href="/mysite/user?action=logout" class="btn_s">로그아웃</a></li>
				<li><a href="/mysite/user?action=modifyForm&no=<%=authUser.getNo()%>" class="btn_s">회원정보수정</a></li>
			</ul>
		</c:otherwise>
	</c:choose>

</div>

<div id="nav">
	<ul class="clearfix">
		<li><a href="">입사지원서</a></li>
		<li><a href="">게시판</a></li>
		<li><a href="">갤러리</a></li>
		<li><a href="/mysite/user?action=addList">방명록</a></li>
	</ul>
</div>


