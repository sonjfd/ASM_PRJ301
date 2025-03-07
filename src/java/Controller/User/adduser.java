/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import model.Users;

/**
 *
 * @author Hung
 */
@MultipartConfig
public class adduser extends HttpServlet {

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
            out.println("<title>Servlet adduser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adduser at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("view/admin/adduser.jsp").forward(request, response);
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
        try {
        UserDAO dao = new UserDAO();
        ArrayList<Users> listUser = dao.getAllUser();

        String name = request.getParameter("fullName");
        String account = request.getParameter("account");

        // Kiểm tra tài khoản đã tồn tại chưa
        for (Users user : listUser) {
            if (account.equals(user.getAccount())) {
                request.setAttribute("message", "Tài khoản đã tồn tại");
                request.getRequestDispatcher("view/admin/adduser.jsp").forward(request, response);
                return;
            }
        }

        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        Part part = request.getPart("avatar");
        int status = 1;
        String role = request.getParameter("role");

        String realPath = request.getServletContext().getRealPath("assets/images");
        File uploads = new File(realPath);
        if (!uploads.exists()) {
            uploads.mkdirs();
        }

        String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
        String filePath = "assets/images/" + filename;

        File file = new File(uploads, filename);
        part.write(file.getAbsolutePath());

        Date created_at = new Date(); 
        Date updated_at = null;

        Users newUser = new Users(name, account, password, email, phone, gender, address, filePath, status, role, created_at, updated_at);

        if (dao.addUser(newUser)) {
            request.setAttribute("message", "Thêm thành công");
            response.sendRedirect("user");
        } else {
            request.setAttribute("message", "Thêm không thành công");
        }

        
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("message", "Lỗi hệ thống, vui lòng thử lại!");
        request.getRequestDispatcher("view/admin/adduser.jsp").forward(request, response);
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
