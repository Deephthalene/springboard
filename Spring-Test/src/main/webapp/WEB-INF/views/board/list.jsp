<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style type="text/css">
h1{
   text-align: center;
}
.container{
   text-align: center;
   display: flex;
   justify-content: center;
}
.reg{
   margin-left: 5px;
}
</style>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<h1>게시판</h1>
<div class="container">
<table class="table">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${list}" var="bvo">
    <tr>
       <td scope="row">${bvo.bno}</td>
       <td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
       <td>${bvo.writer}</td>
       <td>${bvo.registerDate}</td>
      <td>${bvo.read_count}</td>
    </tr>
    </c:forEach>
  
  </tbody>
</table>
</div>

<!-- paging line -->
<div>
      <!-- pgh.prev true이면 -->
      <c:if test="${pgh.prev}">
         <a href="/board/list?pageNo=${pgh.startPage - 1}&qty=${pgh.pgvo.qty}">◁</a>
      </c:if>
      <c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="i">
         <a href="/board/list?pageNo=${i}&qty=${pgh.pgvo.qty}">${i} ｜ </a>
      </c:forEach>
      <c:if test="${pgh.next}">
         <a href="/board/list?pageNo=${pgh.endPage + 1}&qty=${pgh.pgvo.qty}">▷</a>
      </c:if>
   </div>

<div class="container">
   <a href="/member/"><button type="submit" class="btn btn-outline-dark">메인으로 이동</button></a>
   <a href="/board/register"><button type="submit" class="btn btn-outline-dark reg">글작성</button></a>
   </div>
   <jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>