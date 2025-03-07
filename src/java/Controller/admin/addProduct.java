/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import DAO.CategoriesDAO;
import DAO.ColorDAO;
import DAO.ProductDAO;
import DAO.RatingDAO;
import DAO.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categories;
import model.Color;
import model.Product;
import model.Size;

/**
 *
 * @author Dell
 */
@MultipartConfig
public class addProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriesDAO dao = new CategoriesDAO();
        ColorDAO colorDao = new ColorDAO();
        SizeDAO sizeDao = new SizeDAO();
        ArrayList<Categories> list1 = dao.getAllCategories();
        request.setAttribute("listcategori", list1);
        ArrayList<Size> list2 = sizeDao.getAll();
        request.getRequestDispatcher("/view/admin/addproduct.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pdao=new ProductDAO();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String content = request.getParameter("content");
        int cid = Integer.parseInt(request.getParameter("cid"));
  
        Part part = request.getPart("avatar");
         String realPath = request.getServletContext().getRealPath("/assets/images");
        String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

        File uploads = new File(realPath);
        if (!uploads.exists()) {
            uploads.mkdirs();
        }
        File file = new File(uploads, filename);
        part.write(file.getAbsolutePath());
        Product p;
        
        Categories  c = (new CategoriesDAO().getCategoriesByid(cid));
            
            p = new Product(c, name, description, content, "/assets/images/" + filename, 1, 0, 0, 0, new java.util.Date(),new java.util.Date());
            int result=pdao.insertProduct(p);
            if (result > 0) {
            response.sendRedirect("listproduct"); 
        } 
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
