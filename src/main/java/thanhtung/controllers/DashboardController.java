package thanhtung.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import thanhtung.models.CategoryModel;
import thanhtung.services.CategoryServices;
import thanhtung.services.impl.CategoryServiceImpl;

public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryServices categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<CategoryModel> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.setAttribute("pageTitle", "Dashboard");
        RequestDispatcher rd = req.getRequestDispatcher("/views/dashboard.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}