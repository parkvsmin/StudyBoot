<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
    </head>
    <c:import url="../temp/boot.jsp"></c:import>
    <c:import url="../temp/summernote.jsp"></c:import>
    <script defer type="text/javascript" src="/js/fileAdd.js"></script>

    <body>
    <div class="container-fluid">
      <h1>Board Write Page</h1>
      <div>
        <form:form modelAttribute="qnaVO" method="post" enctype="multipart/form-data">
          <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <form:input path="title" cssClass="form-control" id="title"/>
            <form:errors path="title"></form:errors>
          </div>
          
          <div class="mb-3">
            <label for="writer" class="form-label">작성자</label>
          <form:input path="writer" cssClass="form-control" id="writer"/>
          <form:errors path="writer"></form:errors>
          </div>
          
          <div class="mb-3">
            <label for="contents" class="form-label">내용</label>
          <form:input path="contents" cssClass="form-control" id="contents"/>
          <form:errors path="contents"></form:errors>
          </div>

          <div class="mb-3" id="fileAddResult"></div>
          <div class="mb-3">
            <button type="button" id="fileAdd">FileAdd</button>
          </div>

          <!-- 		<div class="mb-3">
  <label for="contents" class="form-label">File</label>
  <input type="file" name="files" class="form-control" id="exampleFormControlInput1">
</div>
		<div class="mb-3">
  <label for="contents" class="form-label">File</label>
  <input type="file" name="files" class="form-control" id="exampleFormControlInput1">
</div> -->
  <div>
    <button type="submit" class="btn btn-danger">작성</button>
  </div>
        </form:form>
      </div>
      </div>
      
      <script type="text/javascript">
        $('#contents').summernote({
          tabsize: 4,
          height: 250
        });
      </script>
    </body>
    </html>