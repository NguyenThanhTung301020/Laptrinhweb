package thanhtung.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import thanhtung.models.CategoryModel;
import thanhtung.services.CategoryServices;
import thanhtung.services.impl.CategoryServiceImpl;

public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CategoryServices categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            List<CategoryModel> categories = categoryService.getAllCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/views/category/list.jsp").forward(req, resp);
        } else if ("add".equals(action)) {
            req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                CategoryModel category = categoryService.getCategoryById(id);
                if (category != null) {
                    req.setAttribute("category", category);
                    req.getRequestDispatcher("/views/category/edit.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/category");
                }
            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/category");
            }
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                categoryService.deleteCategory(id);
            } catch (NumberFormatException e) {
                // Ignore invalid ID
            }
            resp.sendRedirect(req.getContextPath() + "/category");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CategoryModel category = new CategoryModel();
        String currentImage = "";
        String action = "";
        
        try {
            if (ServletFileUpload.isMultipartContent(req)) {
                // Xử lý multipart form (upload file)
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");
                
                List<FileItem> items;
                try {
                    items = upload.parseRequest(req);
                } catch (FileUploadException e) {
                    req.setAttribute("alert", "Lỗi upload file: " + e.getMessage());
                    req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
                    return;
                }
                
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String fieldName = item.getFieldName();
                        String value = item.getString("UTF-8");
                        if ("action".equals(fieldName)) action = value;
                        if ("id".equals(fieldName)) category.setId(Integer.parseInt(value));
                        if ("name".equals(fieldName)) category.setName(value);
                        if ("description".equals(fieldName)) category.setDescription(value);
                        if ("currentImage".equals(fieldName)) currentImage = value;
                    } else {
                        // Xử lý file upload
                        String fileName = item.getName();
                        if (!fileName.isEmpty()) {
                            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) uploadDir.mkdir();
                            
                            // Tạo tên file unique
                            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                            File uploadedFile = new File(uploadPath + File.separator + uniqueFileName);
                            
                            try {
                                item.write(uploadedFile);
                                category.setImage("uploads/" + uniqueFileName);
                            } catch (Exception e) {
                                e.printStackTrace();
                                req.setAttribute("alert", "Lỗi lưu file: " + e.getMessage());
                                req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
                                return;
                            }
                        }
                    }
                }
                
                // Nếu không upload file mới, giữ image cũ
                if (category.getImage() == null || category.getImage().isEmpty()) {
                    category.setImage(currentImage);
                }
            } else {
                // Form thường (không có file upload)
                action = req.getParameter("action");
                try {
                    category.setId(Integer.parseInt(req.getParameter("id")));
                } catch (NumberFormatException e) {
                    category.setId(0);
                }
                category.setName(req.getParameter("name"));
                category.setDescription(req.getParameter("description"));
                category.setImage(req.getParameter("currentImage"));
            }

            // Xử lý action
            if ("add".equals(action)) {
                if (categoryService.addCategory(category)) {
                    resp.sendRedirect(req.getContextPath() + "/category");
                } else {
                    req.setAttribute("alert", "Thêm thất bại! Vui lòng kiểm tra tên category.");
                    req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
                }
            } else if ("edit".equals(action)) {
                if (categoryService.updateCategory(category)) {
                    resp.sendRedirect(req.getContextPath() + "/category");
                } else {
                    req.setAttribute("alert", "Cập nhật thất bại!");
                    req.setAttribute("category", category);
                    req.getRequestDispatcher("/views/category/edit.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("alert", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/views/category/add.jsp").forward(req, resp);
        }
    }
}