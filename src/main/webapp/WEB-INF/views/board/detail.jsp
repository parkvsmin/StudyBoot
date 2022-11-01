<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	
	<h1>Detail Page</h1>
	<h3>Title : ${detail.title}</h3>
	
	<c:forEach items="${detail.qnaFileVOs}" var="fileVO">
		<img alt="" src="/file/qna/${fileVO.fileName}">
		<a href="/fileDown/qna?fileNum=${fileVO.fileNum}">${fileVO.oriName}</a>
	</c:forEach>
	<table border="1">
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>글제목</th>
					<th>글내용</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				
				<tr>
				<td>${detail.num}</td>
				<td>${detail.writer}</td>
				<td>${detail.title}</td>
				<td>${detail.contents}</td>
				<td>${detail.hit}</td>
				<td>${detail.regDate}</td>
			</tr>
	</table>
	
<div>
		<a href="update?num=${detail.num}">Update</a>
	</div>
	
	
	
</body>
</html>