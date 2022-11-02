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
	<h1>List Page</h1>
	
	<table class="table table-striped">
				<thead>
					<tr>
						<th>num</th>
						<th>writer</th>
						<th>title</th>
						<th>regDate</th>
						<th>hit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="dto">
						<tr>
						<c:catch>
						<c:forEach begin="1" end="${dto.depth}">--</c:forEach>
						</c:catch>
							<td><a href="detail?num=${dto.num}">${dto.num}</a></td>
							<td>${dto.title}</td>
							<td>${dto.writer}</td>
							<td>${dto.regDate}</td>
							<td>${dto.hit}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="mb-3">
				<a href="./add" class="btn btn-primary">글쓰기</a>
			</div>
			
			<script type="text/javascript">
			let result = '${param.result}';
			if(result !="") {
				if(result =='1') {
					alert('등록성공');
				}else {
					alert('등록실패');
				}
			}
			</script>
</body>
</html>

