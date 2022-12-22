<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="./layout/header.jsp"></jsp:include>

<style>
h1{
text-align: center;
}
</style>
<h1>Hello world!</h1>

<P>My Spring Project</P>

<jsp:include page="./layout/footer.jsp"></jsp:include>

	<script type="text/javascript">
	const msg = '<c:out value="${msg}"/>'
	if(msg==='0'){
		console.log(msg);
		alert("로그아웃 되었습니다");
	}
	</script>
