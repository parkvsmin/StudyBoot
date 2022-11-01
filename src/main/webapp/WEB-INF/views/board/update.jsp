<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      <h1>Update Page</h1>
      
      <form action="update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="num" value="${qnaVO.num}">
        <div class="mb-3">

          <label for="title" class="form-label">제목</label> <input type="text" name="title" value="${qnaVO.title}"
            class="form-control" id="title" placeholder="제목 입력">
        </div>
        <div class="mb-3">

          <label for="writer" class="form-label">작성자</label> <input type="text" name="writer" value="${qnaVO.writer}"
            class="form-control" id="Writer" placeholder="작성자 입력">
        </div>
        
        <div class="mb-3">
          <label for="contents" class="form-label">Contents</label>
          <textarea class="form-control" name="contents" id="contents">${qnaVO.contents}</textarea>
        </div>

        <div class="mb-3" id="fileAddResult" data-file-size="${qnaVO.qnaFileVOs.size()}">
          <c:forEach items="${qnaVO.qnaFileVOs}" var="fileVO">
            <p>${fileVO.oriName}<button type="button" class="deleteFile"
                data-File-num="${fileVO.fileNum}">X</button></p>
          </c:forEach>
        </div>

        <div class="mb-3">
          <button type="button" id="fileAdd">FileAdd</button>
        </div>

        <!-- <c:forEach items="${qnaVO.qnaFileVOs}" var="qnaFileVO">
          <div class="mb-3">
            <span class="form-control">${qnaFileVO.oriName}</span>
            <button type="button" class="fileDelete" data-file-num="${qnaFileVO.fileNum}">삭제</button>
          </div>
        </c:forEach> -->

        <!-- <div class="mb-3">
          <a href="./delete" class="btn btn-primary">글삭제</a>
        </div> -->

        <div class="mb-3">
          <button class="btn btn-primary">제출</button>
        </div>

      </form>

      <script type="text/javascript">
        $('#contents').summernote({
          tabsize: 4,
          height: 250
        });

        $('#contents').summernote('code', '${qnaVO.contents}')
      </script>

    </body>

    </html>