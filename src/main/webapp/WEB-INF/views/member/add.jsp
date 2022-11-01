<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<form:form modelAttribute="memberVO" method="post">
				<div class="row">
					<label for="inputId" class="col-sm-2 col-form-label">ID</label>
					<div class="col">
						<form:input path="id" cssClass="form-control" id="inputId" />
						<div id="idResult">
						<form:errors path="id"></form:errors>
						</div>
					</div>
				</div>
				<p>
				<div class="row mb-3">
					<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-sm-10">
						<form:password path="password" cssClass="form-control"
							id="inputPassword" />
						<form:errors path="password"></form:errors>
						<div id="pwResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
					<div class="col-sm-10">
						<form:password path="passwordCheck" cssClass="form-control"
							id="inputPasswordCheck" />
						<form:errors path="passwordCheck"></form:errors>
						<div id="pwcResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputName" class="col-sm-2 col-form-label">Name</label>
					<div class="col-sm-10">
						<form:input path="name" cssClass="form-control" id="inputName" />
						<%-- <form:errors path="name"></form:errors> --%>
						<div id="nameResult">
						${name}
						</div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
					<div class="col-sm-10">
						<form:input path="email" cssClass="form-control" id="inputEmail" />
						<form:errors path="email"></form:errors>
						<div id="emailResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputAge" class="col-sm-2 col-form-label">Age</label>
					<div class="col-sm-10">
						<form:input path="age" cssClass="form-control" id="inputAge" />
						<form:errors path="age"></form:errors>
						<div id="ageResult"></div>
					</div>
				</div>

				<div class="row mb-3">
					<label for="inputBirth" class="col-sm-2 col-form-label">Birth</label>
					<div class="col-sm-10">
						<form:input path="birth" cssClass="form-control" id="inputBirth" />
						<form:errors path="birth"></form:errors>
						<div id="birthResult"></div>
					</div>
				</div>

				<div class="row justify-content-end">
					<div>
						<button type="submit" id="addButton" class="btn btn-primary mb-3">Sign
							in</button>
					</div>
				</div>
			</form:form>
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