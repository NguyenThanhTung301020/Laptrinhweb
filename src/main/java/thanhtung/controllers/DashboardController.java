package thanhtung.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import thanhtung.models.UserModel;

public class DashboardController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        UserModel user = (UserModel) session.getAttribute("account");
        req.setAttribute("currentUser", user);
        req.setAttribute("pageTitle", "Dashboard - LT Web");
        
        RequestDispatcher rd = req.getRequestDispatcher("/views/dashboard.jsp");
        rd.forward(req, resp);
    }
}