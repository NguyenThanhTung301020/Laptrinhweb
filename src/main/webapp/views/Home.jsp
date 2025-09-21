<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>

	<c:choose>
		<c:when test="${sessionScope.account == null}">
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a href="${pageContext.request.contextPath}/login">Đăng nhập</a> | <a href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="col-sm-6">
				<ul class="list-inline right-topbar pull-right">
					<li><a href="${pageContext.request.contextPath}/member/myaccount">${sessionScope.account.fullname}</a>
						| <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
					<li><i class="search fa fa-search search-button"></i></li>
				</ul>
			</div>
			<h2>Chào ${sessionScope.account.fullname} (Role: ${sessionScope.account.roleid})</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>