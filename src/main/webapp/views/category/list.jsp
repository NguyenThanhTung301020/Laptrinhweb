<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách Category</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; margin-right: 5px; color: white; }
        .btn-primary { background: #007bff; }
        .btn-warning { background: #ffc107; color: #333; }
        .btn-danger { background: #dc3545; }
        .image-preview { max-width: 50px; height: auto; }
    </style>
</head>
<body>
    <h1>Danh sách Category</h1>
    <a href="${pageContext.request.contextPath}/category?action=add" class="btn btn-primary">
        <i class="fa fa-plus"></i> Thêm mới
    </a>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Ảnh</th>
                <th>Tên</th>
                <th>Mô tả</th>
                <th>Ngày tạo</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.id}</td>
                    <td>
                        <c:if test="${not empty category.image}">
                            <img src="${pageContext.request.contextPath}/${category.image}" 
                                 class="image-preview" alt="Category Image">
                        </c:if>
                        <c:if test="${empty category.image}">
                            <span>Chưa có ảnh</span>
                        </c:if>
                    </td>
                    <td>${category.name}</td>
                    <td>${category.description}</td>
                    <td>${category.createdDate}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/category?action=edit&id=${category.id}" 
                           class="btn btn-warning"><i class="fa fa-edit"></i> Sửa</a>
                        <a href="${pageContext.request.contextPath}/category?action=delete&id=${category.id}" 
                           class="btn btn-danger" onclick="return confirm('Xóa category này?')">
                           <i class="fa fa-trash"></i> Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>