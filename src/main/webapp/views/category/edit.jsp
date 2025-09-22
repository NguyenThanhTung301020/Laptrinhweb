<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Category</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], textarea { width: 100%; max-width: 400px; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
        textarea { height: 100px; }
        .btn { padding: 10px 20px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .btn:hover { background: #0056b3; }
        .current-image { margin: 10px 0; }
        .current-image img { max-width: 200px; height: auto; border: 1px solid #ddd; border-radius: 4px; }
    </style>
</head>
<body>
    <h1>Sửa Category: ${category.name}</h1>
    <c:if test="${alert != null}">
        <div style="color: red; margin-bottom: 10px;">${alert}</div>
    </c:if>
    
    <form action="${pageContext.request.contextPath}/category" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="${category.id}">
        <input type="hidden" name="currentImage" value="${category.image}">
        
        <div class="form-group">
            <label for="name">Tên Category:</label>
            <input type="text" id="name" name="name" value="${category.name}" required>
        </div>
        
        <div class="form-group">
            <label for="description">Mô tả:</label>
            <textarea id="description" name="description">${category.description}</textarea>
        </div>
        
        <div class="form-group">
            <label>Ảnh hiện tại:</label>
            <c:if test="${not empty category.image}">
                <div class="current-image">
                    <img src="${pageContext.request.contextPath}/${category.image}" alt="Current Image">
                </div>
            </c:if>
            <label for="image">Ảnh mới:</label>
            <input type="file" id="image" name="image">
        </div>
        
        <div class="form-group">
            <button type="submit" class="btn">Cập nhật</button>
            <a href="${pageContext.request.contextPath}/category">
                <button type="button" class="btn" style="background: #6c757d;">Hủy</button>
            </a>
        </div>
    </form>
</body>
</html>