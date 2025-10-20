<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Trang chủ</title>
</head>
<body>

	<div>
		<%@ include file="/common/web/header.jsp"%>
	</div>
	<hr>

	<div>
		<sitemesh:write property="body" />
	</div>
	<hr>

	<div>
		<%@ include file="/common/footer.jsp"%>
	</div>

</body>
</html>