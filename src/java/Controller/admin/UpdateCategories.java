package Controller.admin;

import DAO.CategoriesDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Categories;

@MultipartConfig
public class UpdateCategories extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updatecategories</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updatecategories at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        CategoriesDAO categoriesDAO = new CategoriesDAO();
        Categories category;
        category = categoriesDAO.getCategoriesByid(id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/view/admin/updatecate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part part = request.getPart("avatar");

        String realPath = request.getServletContext().getRealPath("/assets/images");
        File uploads = new File(realPath);
        if (!uploads.exists()) {
            uploads.mkdirs();
        }

        String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
        String filePath = "/assets/images/" + filename;
        
        if (filename.isEmpty()) {
            CategoriesDAO categoriesDAO = new CategoriesDAO();
            Categories category = categoriesDAO.getCategoriesByid(id);
            filePath = category.getAvatar();
            
        } else {
            File file = new File(uploads, filename);
            part.write(file.getAbsolutePath());
        }

        int status = Integer.parseInt(request.getParameter("status"));
        int hot = Integer.parseInt(request.getParameter("hot"));

        Categories category = new Categories(id, name, description, filePath, status, hot,  new java.util.Date(), new java.util.Date());
        category.setId(id);
        CategoriesDAO categoriesDAO = new CategoriesDAO();
        int result;
        try {
            result = categoriesDAO.updateCate(category);
            if (result > 0) {
                response.sendRedirect("listcategories");
            } else {
                request.setAttribute("message", "Cập nhật danh mục thất bại");
                request.getRequestDispatcher("/view/admin/updatecate.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
