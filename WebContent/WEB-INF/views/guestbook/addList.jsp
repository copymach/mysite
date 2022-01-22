<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%
/*
page import="java.util.List"
page import="com.javaex.vo.GuestbookVo"
page import="com.javaex.vo.UserVo"

System.out.println("addList.jsp 시작");
//guestbook List 가져오기
List<GuestbookVo> guestbookList = (List<GuestbookVo>) request.getAttribute("gList");

System.out.println(guestbookList);

UserVo authUser = (UserVo) session.getAttribute("authUser");
//List<GuestbookVo> gList = (List<GuestbookVo>)request.getAttribute("gList");
*/
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게스트북 방명록 : 로그인없이 글쓰기 가능</title>

<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<!-- //header -->

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="/mysite/guest" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>

								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->

						<input type="hidden" name="action" value="write">

					</form>

					<c:forEach items="${requestScope.guestbookList}" var="guestbookList">
					
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>${guestbookList.no}</td>
							<td>${guestbookList.name}</td>
							<td>${guestbookList.regDate }</td>
							<td><a href="/mysite/guest?action=deleteForm&no=${guestbookList.no}">[삭제]</a></td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">${guestbookList.content} </td>
						</tr>
					</table>
					<!-- //guestRead -->

					</c:forEach>


				</div>
				<!-- //guestbook -->

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