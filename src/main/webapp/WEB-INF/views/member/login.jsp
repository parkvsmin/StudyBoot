<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>
	<h1>Login Page</h1>
	
	<div>
	<h3>${param.error}</h3>
	<h3>${param.message}</h3>
	<h3>${msg}</h3>
	</div>

	<section class="container-fluid ">
		<div class="row">
			<form action="./login" method="post" id="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div class="mb-3">
					<label for="id" class="form-label">ID</label> <input type="text"
						name="id" value="${cookie.userId.value}" class="form-control"
						id="id" aria-describedby="emailHelp">
					<div id="emailHelp" class="form-text">We'll never share your
						email with anyone else.</div>
				</div>

				<div class="mb-3">
					<label for="password" class="form-label">Password</label> <input
						type="password" name="password" value="admin1" class="form-control" id="password">
				</div>

				<div class="mb-3 form-check">
					<input type="checkbox" name="rememberId" class="form-check-input"
						id="exampleCheck1"> <label class="form-check-label"
						for="exampleCheck1">ID 기억하기</label>
				</div>
				
				<input type="checkbox" name="rememberMe" class="form-check-input" id="exampleCheck2">RememberMe
				<button type="submit" class="btn btn-primary" id="btn">Submit</button>
			</form>

		</div>
	</section>
	<!-- 주소창 파라미터 -->
	<script type="text/javascript">
		//history.replaceState({}, null, location.pathname)
	</script>


</body>
</html>