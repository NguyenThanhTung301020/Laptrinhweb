package thanhtung.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import thanhtung.models.UserModel;
import thanhtung.services.UserServices;
import thanhtung.services.impl.UserServiceImpl;

public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    UserServices service = new UserServiceImpl();
                    UserModel user = service.findByUserName(cookie.getValue());
                    if (user != null) {
                        session = req.getSession(true);
                        session.setAttribute("account", user);
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                        return;
                    }
                }
            }
        }
        
        req.getRequestDispatcher("/views/admin/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        String alertMsg = "";
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || fullname == null || fullname.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            alertMsg = "Không được để trống các trường!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/admin/register.jsp").forward(req, resp);
            return;
        }

        UserServices service = new UserServiceImpl();

        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/admin/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/admin/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/admin/register.jsp").forward(req, resp);
            return;
        }

        boolean isSuccess = service.register(email, password, username, fullname, phone);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/login?success=Đăng ký thành công!");
        } else {
            alertMsg = "Lỗi hệ thống!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/admin/register.jsp").forward(req, resp);
        }
    }
}