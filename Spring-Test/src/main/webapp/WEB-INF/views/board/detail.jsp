<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<h1>Board Detail Page</h1>
   <table border="1" width="350px">
      <tr>
         <th>번호</th>
         <td>${board.bno}</td>
      </tr>
      <tr>
         <th>제목</th>
         <td>${board.title}</td>
      </tr>
      <tr>
         <th>작성자</th>
         <td>${board.writer}</td>
      </tr>
      <tr>
         <th>내용</th>
         <td>${board.content}</td>
      </tr>
      <tr>
         <th>작성일</th>
         <td>${board.registerDate}</td>
      </tr>
      <tr>
         <th>조회수</th>
         <td>${board.read_count}</td>
      </tr>
   </table>
   <c:if test="${ses != null || ses.id == board.writer}">
      <a href="/board/modify?bno=${board.bno}"><button type="submit" class="btn btn-outline-dark">수정</button></a>
      <a href="/board/remove?bno=${board.bno}"><button type="submit" class="btn btn-outline-dark">삭제</button></a>
   </c:if>
   <a href="/board/list"><button type="submit" class="btn btn-outline-dark">리스트</button></a>
   <jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>