<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<style type="text/css">
h1 {
	text-align: center;
}

.container{
	display : flex;
	justify-content: center;
	text-align: center;
}

.reg{
margin-left : 10px;
}
</style>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>

	<h1>게시판</h1>
	<div class="container">
	<table class="table table-borderless">
		<thead>
			<tr>
				<th scope="col">No.</th>
				<th scope="col">Title</th>
				<th scope="col">Writer</th>
				<th scope="col">Register Date</th>
				<th scope="col">Read_Count</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.registerDate }</td>
					<td>${bvo.read_count }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="container">
		<a href="/member/"><button type="submit"
				class="btn btn-outline-dark">Main</button></a>
		
		<a href="/board/register"><button type="submit"
				class="btn btn-outline-dark reg">글 작성</button></a>
	</div>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>