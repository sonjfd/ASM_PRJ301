/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.admin;

import DAO.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.OrderDetail;
import model.Orders;

/**
 *
 * @author ADMIN
 */
public class bill extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         // 1. Lấy orderId từ tham số URL
        String orderIdParam = request.getParameter("orderId");
        if (orderIdParam == null || orderIdParam.trim().isEmpty()) {
            // Nếu không có orderId thì chuyển hướng về trang danh sách đơn hàng (hoặc báo lỗi)
            response.sendRedirect("listorder?page=1");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdParam);

            // 2. Gọi DAO để lấy thông tin Order
            OrderDAO orderDAO = new OrderDAO();
            Orders order = orderDAO.getOrderById(orderId);
            if (order == null) {
                // Không tìm thấy đơn hàng -> chuyển hướng (hoặc hiển thị thông báo)
                response.sendRedirect("listorder1page=1");
                return;
            }

            // 3. Lấy chi tiết đơn hàng
            List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);

            // 4. Đưa dữ liệu lên request
            request.setAttribute("ORDER", order);
            request.setAttribute("ORDER_DETAILS", orderDetails);

            // 5. Forward sang trang bill.jsp (trang in hóa đơn)
            request.getRequestDispatcher("/view/admin/bill.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Nếu parseInt thất bại -> chuyển hướng về list
            response.sendRedirect("listorder?page=1");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
