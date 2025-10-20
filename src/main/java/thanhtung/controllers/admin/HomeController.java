package thanhtung.controllers.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {  // ← XÓA @WebServlet

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Tạm comment để test layout mà không login
        // if (req.getSession().getAttribute("account") == null) {
        //     resp.sendRedirect(req.getContextPath() + "/login");
        //     return;
        // }
        
        req.setAttribute("pageTitle", "Admin Dashboard");
        req.setAttribute("currentPage", "admin-home");
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/Home.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}