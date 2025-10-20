package thanhtung.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import thanhtung.models.CategoryModel;
import thanhtung.services.CategoryServices;
import thanhtung.services.impl.CategoryServiceImpl;
import thanhtung.utils.Constant;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = Constant.MAX_FILE_SIZE, // 5MB
    maxRequestSize = Constant.MAX_FILE_SIZE * 5 // 25MB
)
public class CategoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryServices categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listCategories(req, resp);
        } else {
            switch (action) {
                case "add":
                    req.getRequestDispatcher(Constant.CATEGORY_ADD_PATH).forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    CategoryModel category = categoryService.getCategoryById(id);
                    req.setAttribute("category", category);
                    req.getRequestDispatcher(Constant.CATEGORY_EDIT_PATH).forward(req, resp);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(req.getParameter("id"));
                    categoryService.deleteCategory(deleteId);
                    resp.sendRedirect(req.getContextPath() + "/category");
                    break;
                default:
                    listCategories(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String uploadPath = getServletContext().getRealPath("/") + Constant.UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = "";
        try {
            Part filePart = req.getPart("image");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Kiểm tra extension
                boolean allowed = false;
                for (String ext : Constant.ALLOWED_EXTENSIONS) {
                    if (originalFileName.toLowerCase().endsWith(ext)) {
                        allowed = true;
                        break;
                    }
                }
                if (!allowed) {
                    req.setAttribute("alert", "Chỉ cho phép file: " + String.join(", ", Constant.ALLOWED_EXTENSIONS));
                    forwardWithError(req, resp, action);
                    return;
                }
                // Kiểm tra size (đã xử lý bởi @MultipartConfig)
                fileName = System.currentTimeMillis() + "_" + originalFileName; // Tránh trùng tên
                filePart.write(uploadPath + File.separator + fileName);
                fileName = Constant.UPLOAD_DIRECTORY + "/" + fileName;
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("alert", Constant.MSG_UPLOAD_ERROR);
            forwardWithError(req, resp, action);
            return;
        }

        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            CategoryModel category = new CategoryModel();
            category.setName(name);
            category.setDescription(description);
            category.setImage(fileName);
            category.setCreatedDate(new Date(System.currentTimeMillis()));
            boolean success = categoryService.addCategory(category);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/category");
            } else {
                req.setAttribute("alert", "Thêm thất bại!");
                req.getRequestDispatcher(Constant.CATEGORY_ADD_PATH).forward(req, resp);
            }
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String currentImage = req.getParameter("currentImage");
            CategoryModel category = new CategoryModel();
            category.setId(id);
            category.setName(name);
            category.setDescription(description);
            category.setImage(fileName.isEmpty() ? currentImage : fileName);
            boolean success = categoryService.updateCategory(category);
            if (success) {
                resp.sendRedirect(req.getContextPath() + "/category");
            } else {
                req.setAttribute("alert", Constant.MSG_UPDATE_FAILED);
                req.setAttribute("category", category);
                req.getRequestDispatcher(Constant.CATEGORY_EDIT_PATH).forward(req, resp);
            }
        }
    }

    private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryModel> categories = categoryService.getAllCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher(Constant.CATEGORY_LIST_PATH).forward(req, resp);
    }

    private void forwardWithError(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        if ("add".equals(action)) {
            req.getRequestDispatcher(Constant.CATEGORY_ADD_PATH).forward(req, resp);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            CategoryModel category = categoryService.getCategoryById(id);
            req.setAttribute("category", category);
            req.getRequestDispatcher(Constant.CATEGORY_EDIT_PATH).forward(req, resp);
        }
    }
}