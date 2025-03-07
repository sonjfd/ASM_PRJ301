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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Orders;

/**
 *
 * @author ADMIN
 */
public class listorder extends HttpServlet {

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
         try {
        OrderDAO dao = new OrderDAO();
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        
        // Lấy tham số lọc cho trạng thái thanh toán và vận chuyển
        String paymentStatusParam = request.getParameter("paymentStatus");
        Integer paymentStatus = null;
        if (paymentStatusParam != null && !paymentStatusParam.isEmpty()) {
            paymentStatus = Integer.parseInt(paymentStatusParam);
        }
        String transportStatusParam = request.getParameter("transportStatus");
        Integer transportStatus = null;
        if (transportStatusParam != null && !transportStatusParam.isEmpty()) {
            transportStatus = Integer.parseInt(transportStatusParam);
        }
        
        // Lấy tham số lọc theo ngày (định dạng yyyy-MM-dd)
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateParam = request.getParameter("fromDate");
        Date fromDate = null;
        if (fromDateParam != null && !fromDateParam.isEmpty()) {
            fromDate = sdf.parse(fromDateParam);
        }
        String toDateParam = request.getParameter("toDate");
        Date toDate = null;
        if (toDateParam != null && !toDateParam.isEmpty()) {
            toDate = sdf.parse(toDateParam);
        }
        
        // Sử dụng phương thức filterOrders thay cho searchOrders
        List<Orders> list = dao.filterOrders(keyword, paymentStatus, transportStatus, fromDate, toDate);

        int recordsPerPage = 7;
        int totalRecords = list.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecords);

        ArrayList<Orders> paginatedOrders = new ArrayList<>(list.subList(start, end));

        request.setAttribute("ORDER_LIST", paginatedOrders);
        request.setAttribute("SEARCH_KEYWORD", keyword);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        
        // Truyền luôn các tham số lọc sang JSP để giữ giá trị đã chọn
        request.setAttribute("paymentStatus", paymentStatusParam);
        request.setAttribute("transportStatus", transportStatusParam);
        request.setAttribute("fromDate", fromDateParam);
        request.setAttribute("toDate", toDateParam);
        
        request.getRequestDispatcher("/view/admin/orderlist.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách đơn hàng.");
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
