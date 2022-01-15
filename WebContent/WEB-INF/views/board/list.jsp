<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.BoardVo"%>
<%@ page import="com.javaex.vo.UserVo"%>

<%
//System.out.println("Board 게시판 list.jsp 시작"); 동작확인

List<BoardVo> boardList = (List<BoardVo>) request.getAttribute("bList");

//System.out.println(boardList); 동작확인

//BoardVo authUser = (BoardVo) session.getAttribute("authUser");
UserVo authUser = (UserVo) session.getAttribute("authUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySite > board</title>
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
					<div id="list">
						<form action="/mysite/board" method="get">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>

							<%-- 나중에 포이치로 구현해보자 <c:forEach items="${boardList}" var="boardVo"> --%>
							<%
							for (int i = 0; i < boardList.size(); i++) {
							%>

							<tbody>
								<tr>
									<td><%=boardList.get(i).getNo()%></td>
									<td class="text-left"><a href="/mysite/board?action=read&no=<%=boardList.get(i).getNo()%> "> <%=boardList.get(i).getTitle()%></a></td>
									<td><%=boardList.get(i).getUser_name()%> 유저번호:<%=boardList.get(i).getUser_no()%></td>
									<td><%=boardList.get(i).getHit()%></td>
									<td><%=boardList.get(i).getReg_date()%></td>
									<td>
									<c:choose>
										<c:when test="${empty sessionScope.authUser}">
											<!-- 세션 영역에 값이 없으면 로그인 실패, 로그인 전에 사용 -->
											<ul>
												<li> [ - ] </li>
											</ul>
										</c:when>

										<c:otherwise>
											<!-- 로그인 성공시 출력 -->
											<ul>
												<a href="/mysite/board?action=delete&no=<%=boardList.get(i).getNo()%>&userNo=<%=boardList.get(i).getUser_no() %>">[삭제]</a>
											</ul>
										</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</tbody>
							<%
							}
							%>


						</table>

						<div id="paging">
							<ul>
								<li><a href="">◀</a></li>
								<li><a href="">1</a></li>
								<li><a href="">2</a></li>
								<li><a href="">3</a></li>
								<li><a href="">4</a></li>
								<li class="active"><a href="">5</a></li>
								<li><a href="">6</a></li>
								<li><a href="">7</a></li>
								<li><a href="">8</a></li>
								<li><a href="">9</a></li>
								<li><a href="">10</a></li>
								<li><a href="">▶</a></li>
							</ul>


							<div class="clear"></div>
						</div>
						<a id="btn_write" href="">글쓰기</a>

					</div>
					<!-- //list -->
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
