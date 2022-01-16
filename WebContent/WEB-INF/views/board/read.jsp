<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.BoardVo"%>
<%@ page import="com.javaex.vo.UserVo"%>

<%
//Action으로 넘어온 값을 변경시킨후 JSP 페이지로 받아오기
BoardVo boardVo = (BoardVo) request.getAttribute("bdVo");

System.out.println("boardVo 출력 " + boardVo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<!-- //header -->

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- //nav -->


		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="read">
						<form action="#" method="get">
							<!-- 작성자 -->
							<div class="form-group">
								<span class="form-text">작성자</span> <span class="form-value"><%=boardVo.getId()%> Na:<%=boardVo.getUser_name()%> no:<%=boardVo.getUno()%> </span>
							</div>

							<!-- 조회수 -->
							<div class="form-group">
								<span class="form-text">조회수</span> <span class="form-value"><%=boardVo.getHit()%></span>
							</div>

							<!-- 작성일 -->
							<div class="form-group">
								<span class="form-text">작성일</span> <span class="form-value"><%=boardVo.getReg_date()%></span>
							</div>

							<!-- 제목 -->
							<div class="form-group">
								<span class="form-text">제 목</span> <span class="form-value"><%=boardVo.getTitle()%></span>
							</div>

							<!-- 내용 -->
							<div id="txt-content">
								<span class="form-value"> <%=boardVo.getContent()%>
								</span>
							</div>

							<c:choose>
								<c:when test="${empty sessionScope.authUser}">
									<!-- 세션 영역에 값이 없으면 출력 -->
									<a id="btn_modify" href="/mysite/board">목록</a>
								</c:when>

								<c:otherwise>
									<!-- 로그인 성공시 출력 -->
									<a id="btn_modify" href="/mysite/board?action=modifyForm&bno=<%=boardVo.getBno()%>">수정</a>
									<a id="btn_modify" href="/mysite/board">목록</a>
								</c:otherwise>
							</c:choose>

						</form>
						<!-- //form -->
					</div>
					<!-- //read -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

</html>
