<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <!-- Giả sử bạn có CSS và Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        /* CSS đơn giản để giống style form */
        .login-input { margin-bottom: 15px; }
        .alert { color: red; }
    </style>
</head>
<body>
    <form action="register" method="post">
        <h2>Tạo tài khoản mới</h2>
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>
        
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control">
                </div>
            </label>
        </section>
        
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Họ tên" name="fullname" class="form-control">
                </div>
            </label>
        </section>
        
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                    <input type="text" placeholder="Nhập Email" name="email" class="form-control">
                </div>
            </label>
        </section>
        
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                    <input type="text" placeholder="Số điện thoại" name="phone" class="form-control">
                </div>
            </label>
        </section>
        
        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control">
                </div>
            </label>
        </section>
        
        <section>
            <button type="submit" class="btn btn-primary">Tạo tài khoản</button>
        </section>
    </form>
</body>
</html>