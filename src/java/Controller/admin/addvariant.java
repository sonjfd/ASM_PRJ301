/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import DAO.ProductVariantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductVariant;

/**
 *
 * @author Dell
 */
public class addvariant extends HttpServlet {

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
            out.println("<title>Servlet addvariant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addvariant at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String[] productIds = request.getParameterValues("productid[]");
        String[] sizeIds = request.getParameterValues("sizeid[]");
        String[] colorIds = request.getParameterValues("colorid[]");
        if (productIds == null || productIds.length == 0
                || sizeIds == null || sizeIds.length == 0
                || colorIds == null || colorIds.length == 0) {
            request.setAttribute("thongbao", "Bạn phải chọn ít nhất một sản phẩm, dung lượng và màu sắc.");
            request.getRequestDispatcher("/view/admin/listvariant.jsp").forward(request, response);
            return;
        }
        String productIdsStr = String.join(",", productIds);
        String sizeIdsStr = String.join(",", sizeIds);
        String colorIdsStr = String.join(",", colorIds);
        ProductVariantDAO dao = new ProductVariantDAO();
        boolean isCreated;
        try {
            isCreated = dao.createMultiProductVariant(productIdsStr, colorIdsStr, sizeIdsStr);

            if (!isCreated) {
                response.sendRedirect("listvariant");
            } else {
                request.setAttribute("thongbao", "Đã có lỗi xảy ra khi tạo biến thể. Vui lòng thử lại.");
                request.getRequestDispatcher("/view/admin/listvariant.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addvariant.class.getName()).log(Level.SEVERE, null, ex);
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
