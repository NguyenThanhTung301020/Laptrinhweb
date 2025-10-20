<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<h2>Content của USER</h2>
<form method="post" action="${pageContext.request.contextPath}/home">
    Họ: <input type="text" name="holot"> 
    Tên: <input type="text" name="ten"> 
    <input type="submit" value="OK">
</form>