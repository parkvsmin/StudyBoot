<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
<link href="/css/test.css" rel=stylesheet>
<script defer src="/js/test.js"></script>
</head>
<body>
	<h1>Index Page</h1>
	<h1>
		<spring:message code="hi"></spring:message>
	</h1>
	<h1>
		<spring:message code="test" text="code가 없을 때 출력 하는 메세지"></spring:message>
	</h1>
	<div>
		<!-- 로그인 성공 -->
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="Principal" var="member" />
			<h3>
				<spring:message code="welcome" arguments="${member.name}"></spring:message>
			</h3>
			<h3>
				<spring:message code="welcome2"
					arguments="${member.id},${member.name}" argumentSeparator=","></spring:message>
			</h3>
			<a href="./member/add">ADD</a>
			<a href="./qna/list">QNA</a>
			<a href="./member/mypage">MyPage</a>
			<a href="./member/logout">Logout</a>
			<button type="button">KAKAO LOGOUT</button>
			<form action="./member/logout" method="post">
				<sec:csrfInput />
				<button>logout</button>
			</form>
		</sec:authorize>



		<sec:authorize access="!isAuthenticated()">
			<!-- 로그인 전 -->
			<a href="./member/login">LOGIN</a>
			<a href="./oauth2/authorization/kakao">KAKAO LOGIN</a>
			<a href="./member/add">ADD</a>
			<a href="./qna/list">QNA</a>
		</sec:authorize>

		<sec:authorize url="/admin">
			<a href="/admin">Go Admin</a>
		</sec:authorize>

		<sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
			<a href="/manager">Go Manager</a>
		</sec:authorize>

	</div>
	<img src="./images/winter.jpg" id="id1">
	<div>
		<img alt="" src="/file/notice/iu2.jpg"> <img alt=""
			src="/file/qna/5bcfd7a1-4d0f-46ad-8169-19c0b2d7bd47_iu.jpg"> <a
			href="/fileDown/qna?fileNum=${fileNum}">QnaDown</a> <a
			href="/fileDown/notice?fileNum=${fileNum}">NoticeDown</a>
	</div>

	<button id="btn">Click</button>

	<button class="buttons">BTN1</button>
	<button class="buttons">BTN2</button>
	<button class="buttons">BTN3</button>

	<div id="test"></div>

	<script type="text/javascript">
		$("#logout").click(function() {
			$("#outForm").submit();
		})
		
		$("#kakao").click(function() {
			$.get("https://developers.kakao.com/logout", function() {
				location.reload
			})
		})
	</script>

</body>
</html>