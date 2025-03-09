/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import DAO.ColorDAO;
import DAO.ProductDAO;
import DAO.ProductVariantDAO;
import DAO.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Color;
import model.Product;
import model.ProductVariant;
import model.Size;

/**
 *
 * @author Dell
 */
public class ListVariant extends HttpServlet {

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
            out.println("<title>Servlet ListVariant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListVariant at " + request.getContextPath() + "</h1>");
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
        ProductVariantDAO dao = new ProductVariantDAO();
        ArrayList<ProductVariant> list = dao.getAll();
        int numPerPage = 10; 
        int totalVariants = list.size();
        int totalPages = (int) Math.ceil((double) totalVariants / numPerPage);

        String pageStr = request.getParameter("page");
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

        int start = (page - 1) * numPerPage;
        int end = Math.min(start + numPerPage, totalVariants);

        List<ProductVariant> paginatedList = list.subList(start, end); // Chia danh sách theo trang
        request.setAttribute("listvariant", paginatedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        ProductDAO pdao = new ProductDAO();
        ArrayList<Product> list1 = pdao.getAllProduct();
        request.setAttribute("listproduct", list1);

        SizeDAO sdao = new SizeDAO();
        ArrayList<Size> list2 = sdao.getAll();
        request.setAttribute("listsize", list2);

        ColorDAO cdao = new ColorDAO();
        ArrayList<Color> list3 = cdao.getAll();
        request.setAttribute("listcolor", list3);

        request.getRequestDispatcher("/view/admin/listvariant.jsp").forward(request, response);

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
        processRequest(request, response);
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
