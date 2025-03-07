/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import DAO.CategoriesDAO;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categories;
import model.Product;

/**
 *
 * @author Dell
 */
public class FilterProduct extends HttpServlet {

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
            out.println("<title>Servlet FilterProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterProduct at " + request.getContextPath() + "</h1>");
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
        ProductDAO pdao = new ProductDAO();
        ArrayList<Categories> list2;
        list2 = (new CategoriesDAO()).getAllCategories();
        request.setAttribute("categories", list2);

        int cid = 0;
        int minPrice = 0;
        int maxPrice = Integer.MAX_VALUE;
        Date startDate = null;
        Date endDate = null;

        try {
            if (request.getParameter("cid") != null && !request.getParameter("cid").isEmpty()) {
                cid = Integer.parseInt(request.getParameter("cid"));
            }
            
            if (request.getParameter("startDate") != null && !request.getParameter("startDate").isEmpty()) {
                startDate = Date.valueOf(request.getParameter("startDate"));
            }
            if (request.getParameter("endDate") != null && !request.getParameter("endDate").isEmpty()) {
                endDate = Date.valueOf(request.getParameter("endDate"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        ArrayList<Product> products = pdao.FilterProduct(cid,  startDate, endDate);
        request.setAttribute("list", products);
        request.getRequestDispatcher("/view/admin/listproduct.jsp").forward(request, response);

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
