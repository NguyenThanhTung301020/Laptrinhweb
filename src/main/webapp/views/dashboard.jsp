<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body { font-family: Arial, sans-serif; margin: 0; background: #f0f0f0; }
        .sidebar { width: 200px; background: #333; color: white; height: 100vh; position: fixed; padding: 20px; }
        .sidebar h3 { margin-bottom: 20px; }
        .sidebar a { color: white; display: block; margin: 10px 0; text-decoration: none; }
        .sidebar a:hover { color: #ccc; }
        .content { margin-left: 200px; padding: 20px; }
        .content h1 { margin-bottom: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; }
        th { background: #f2f2f2; }
        .btn { padding: 5px 10px; color: white; text-decoration: none; border-radius: 4px; }
        .btn-add { background: #4CAF50; }
        .btn-edit { background: #2196F3; }
        .btn-del { background: #f44336; }
        .image-preview { max-width: 50px; }
    </style>
</head>
<body>
    <div class="sidebar">
        <img src="${pageContext.request.contextPath}/uploads/default-avatar.jpg" alt="Avatar" style="width:100px; border-radius:50%; display: block; margin: 0 auto 20px;">
        <h3 style="text-align: center;">Admin</h3>
        <a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
        <a href="${pageContext.request.contextPath}/category"><i class="fa fa-tags"></i> Quản lý danh mục</a>
        <a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out"></i> Đăng xuất</a>
    </div>
    
    <div class="content">
        <h1>Dashboard</h1>
        <p>Xin chào Admin!</p>
        <h2>Quản lý danh mục</h2>
        <a href="${pageContext.request.contextPath}/category?action=add" class="btn btn-add">Thêm mới</a>
        <table>
            <thead>
                <tr>
                    <th>Ảnh</th>
                    <th>Mã danh mục</th>
                    <th>Tên danh mục</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${categories}">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/${category.image}" class="image-preview" alt="Image">
                        </td>
                        <td>${category.id}</td>
                        <td>${category.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/category?action=edit&id=${category.id}" class="btn btn-edit">Edit</a>
                            <a href="${pageContext.request.contextPath}/category?action=delete&id=${category.id}" class="btn btn-del">Del</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>