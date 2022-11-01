<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
<script defer src="/js/util.js"></script>
<script defer src="/js/memberAdd.js"></script>
</head>
<body>
	<h1>회원가입</h1>

	<div class="row mb-3">
		<div class="col-lg-6">
			<form action="./add" id="addForm" method="post">

				<div class="row">
					<label for="inputUserName" class="col-sm-2 col-form-label">ID</label>
					<div class="col">
						<input type="text" name="id" class="form-control" id="id"
							placeholder="ID 입력">
						<div id="idResult"></div>
					</div>
				</div>
				<p>
				<div class="row mb-3">
					<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control"
							id="password" placeholder="Password 입력">
						<div id="pwResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-sm-10">
						<input type="password" name="passwordCheck" class="form-control"
							id="passwordCheck" placeholder="Password 재입력">
						<div id="pwcResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputName" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<input type="text" name="name" class="form-control" id="name"
							placeholder="이름 입력">
						<div id="nameResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
					<div class="col-sm-10">
						<input type="email" required name="email" class="form-control"
							id="email" placeholder="Email 입력">
						<div id="emailResult"></div>
					</div>
				</div>
			<div class="row justify-content-end">
				<div>
					<button type="button" id="addButton" class="btn btn-primary mb-3">Sign
						in</button>
				</div>
			</div>
			</form>
		</div>
	</div>

	<!--약관 test-->
	<div>
		<div>
			ALL <input type="checkbox" id="all">
		</div>
		<div>
			동의1 <input type="checkbox" class="check" name="" id="">
		</div>
		<div>약관1</div>
		<div>
			동의2 <input type="checkbox" class="check" name="" id="">
		</div>
		<div>약관2</div>
		<div>
			동의3 <input type="checkbox" class="check" name="" id="">
		</div>
		<div>약관3</div>
	</div>


</body>
</html>