<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký - LT Web</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .register-container {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            position: relative;
        }
        .register-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .register-header h2 {
            color: #333;
            font-size: 28px;
            margin-bottom: 10px;
            font-weight: 600;
        }
        .register-header p {
            color: #666;
            font-size: 14px;
        }
        .form-group {
            margin-bottom: 20px;
            position: relative;
        }
        .input-group {
            position: relative;
            display: flex;
            align-items: center;
        }
        .input-group-addon {
            position: absolute;
            left: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #999;
            z-index: 2;
            font-size: 16px;
        }
        .form-control {
            width: 100%;
            padding: 12px 15px 12px 45px;
            border: 2px solid #e1e5e9;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s ease;
            background: #f8f9fa;
        }
        .form-control:focus {
            outline: none;
            border-color: #f093fb;
            background: white;
            box-shadow: 0 0 0 3px rgba(240, 147, 251, 0.1);
        }
        .password-confirm-group {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
            margin-bottom: 25px;
        }
        .btn {
            width: 100%;
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
            padding: 14px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(240, 147, 251, 0.3);
        }
        .alert {
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 8px;
            text-align: center;
            font-weight: 500;
        }
        .alert-danger {
            background: #fee2e2;
            color: #dc2626;
            border: 1px solid #fecaca;
        }
        .login-link {
            text-align: center;
            margin-top: 25px;
            padding-top: 20px;
            border-top: 1px solid #e1e5e9;
        }
        .login-link p {
            color: #666;
            font-size: 14px;
            margin: 0;
        }
        .login-link a {
            color: #f093fb;
            text-decoration: none;
            font-weight: 600;
        }
        .login-link a:hover {
            text-decoration: underline;
        }
        @media (max-width: 480px) {
            .register-container {
                margin: 20px;
                padding: 30px 20px;
            }
            .password-confirm-group {
                grid-template-columns: 1fr;
                gap: 15px;
            }
        }
    </style>
</head>
<body>
    <div class="register-container">
        <div class="register-header">
            <h2>Tạo tài khoản mới</h2>
            <p>Điền thông tin để đăng ký tài khoản</p>
        </div>
        
        <c:if test="${alert != null}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <div class="input-group">
                    <i class="fa fa-user input-group-addon"></i>
                    <input type="text" class="form-control" placeholder="Tài khoản" name="username" 
                           value="${param.username}" required>
                </div>
            </div>
            
            <div class="form-group">
                <div class="input-group">
                    <i class="fa fa-user input-group-addon"></i>
                    <input type="text" class="form-control" placeholder="Họ tên" name="fullname" 
                           value="${param.fullname}" required>
                </div>
            </div>
            
            <div class="form-group">
                <div class="input-group">
                    <i class="fa fa-envelope input-group-addon"></i>
                    <input type="email" class="form-control" placeholder="Nhập Email" name="email" 
                           value="${param.email}" required>
                </div>
            </div>
            
            <div class="form-group">
                <div class="input-group">
                    <i class="fa fa-phone input-group-addon"></i>
                    <input type="tel" class="form-control" placeholder="Số điện thoại" name="phone" 
                           value="${param.phone}" required>
                </div>
            </div>
            
            <div class="password-confirm-group">
                <div class="form-group">
                    <div class="input-group">
                        <i class="fa fa-lock input-group-addon"></i>
                        <input type="password" class="form-control" placeholder="Mật khẩu" name="password" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <i class="fa fa-lock input-group-addon"></i>
                        <input type="password" class="form-control" placeholder="Xác nhận mật khẩu" 
                               name="confirmPassword" required>
                    </div>
                </div>
            </div>
            
            <button type="submit" class="btn">Tạo tài khoản</button>
        </form>
        
        <div class="login-link">
            <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a></p>
        </div>
    </div>
</body>
</html>