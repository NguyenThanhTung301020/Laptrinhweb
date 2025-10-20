<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header">
	<h1>Header của User</h1>
	<c:choose>
		<c:when test="${not empty sessionScope.account}">
			<p>
				Xin chào ${sessionScope.account.username} | <a
					href="${pageContext.request.contextPath}/logout"
					style="color: white;">Đăng xuất</a>
			</p>
		</c:when>
		<c:otherwise>
			<p>
				<a href="${pageContext.request.contextPath}/login"
					style="color: white;">Đăng nhập</a> | <a
					href="${pageContext.request.contextPath}/register"
					style="color: white;">Đăng ký</a>
			</p>
		</c:otherwise>
	</c:choose>
</div>