<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify</title>
<!-- CSS only -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
   crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
   crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<h1>Board Modify Page</h1>
   <form action="/board/modify" method="post">
      <div class="mb-3">
         <label for="formGroupExampleInput" class="form-label">Bno</label> <input
            type="text" class="form-control" id="formGroupExampleInput"
            name="bno" value="${board.bno}" readonly>
      </div>
      <div class="mb-3">
         <label for="formGroupExampleInput" class="form-label">Title</label> <input
            type="text" class="form-control" id="formGroupExampleInput"
            name="title" value="${board.title}">
      </div>
      <div class="mb-3">
         <label for="formGroupExampleInput2" class="form-label">Writer</label>
         <input type="text" class="form-control" id="formGroupExampleInput2"
            name="writer" value="${board.writer}" readonly>
      </div>
      <div class="mb-3">
         <label for="formGroupExampleInput" class="form-label">RegisterDate</label>
         <input type="text" class="form-control" id="formGroupExampleInput"
            name="registerDate" value="${board.registerDate}" readonly></input>
      </div>
      <div class="mb-3">
         <label for="formGroupExampleInput" class="form-label">Content</label>
         <textarea type="text" class="form-control" id="formGroupExampleInput"
            name="content">${board.content}</textarea>
      </div>
      <button type="submit" class="btn btn-outline-dark">수정</button>
   </form>
   <jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>