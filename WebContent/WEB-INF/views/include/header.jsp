<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.UserVo"%>


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

	<%
	if (authUser == null) { // 세션 영역에 값이 없으면 로그인 실패, 로그인 전
	%>

	<ul>
		<li><a href="/mysite/user?action=loginForm" class="btn_s">로그인</a></li>
		<li><a href="/mysite/user?action=joinForm" class="btn_s">회원가입</a></li>
	</ul>

	<%
	} else { // 로그인 성공
	%>

	<ul>
		<!-- 세션에서 갱신한 이름을 가져와보자 -->
		<li><%=authUser.getName()%> 님 안녕하세요^^</li>
		<li><a href="/mysite/user?action=logout" class="btn_s">로그아웃</a></li>
		<li><a href="/mysite/user?action=modifyForm&no=<%=authUser.getNo()%>" class="btn_s">회원정보수정</a></li>
	</ul>

	<%
	}
	%>

</div>

<div id="nav">
	<ul class="clearfix">
		<li><a href="">입사지원서</a></li>
		<li><a href="">게시판</a></li>
		<li><a href="">갤러리</a></li>
		<li><a href="/mysite/user?action=addList">방명록</a></li>
	</ul>
</div>


