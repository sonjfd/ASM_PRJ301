/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.admin;

import DAO.OrderDAO;
import DAO.OrderdeteilsDAO;
import DAO.ProductDAO;
import DAO.ProductVariantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;
import model.Orders;
import model.Product;
import model.ProductVariant;

/**
 *
 * @author ADMIN
 */
public class listorderdetail extends HttpServlet {

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
        // Lấy orderId từ URL
        
//        ProductDAO pdao=new ProductDAO();
//        ProductVariantDAO variantDao=new ProductVariantDAO();
//        
//        Product p=pdao.getProductById();
        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam == null) {
            response.sendRedirect("listorder?page=1");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdParam);

            OrderDAO orderDAO = new OrderDAO();
            // Lấy thông tin đơn hàng
            Orders order = orderDAO.getOrderById(orderId);

            // Nếu không tìm thấy đơn hàng
            if (order == null) {
                response.sendRedirect("listorder?page=1");
                return;
            }

            // Lấy chi tiết các sản phẩm trong đơn hàng
            
            List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);
            

            // Gửi sang JSP
            request.setAttribute("ORDER", order);
            request.setAttribute("ORDER_DETAILS", orderDetails);
            

            // Forward sang trang chi tiết
            request.getRequestDispatcher("/view/admin/orderdetaillist.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("listorder?page=1");
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
