package thanhtung.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {  // ← Xóa @WebServlet

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "Thanh Tùng";

        req.setAttribute("name", name);
        RequestDispatcher rd = req.getRequestDispatcher("/views/Home.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("ten");
        String lstname = req.getParameter("holot");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Hello " + lstname + " " + name);
        out.close();
    }
}