/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import DAO.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
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
import java.sql.Timestamp;
import java.util.Date;
import model.Users;

/**
 *
 * @author Hung
 */
@MultipartConfig
public class updateUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        UserDAO dao = new UserDAO();
        String id_raw = request.getParameter("id");

        try {
            int id = Integer.parseInt(id_raw);
            Users user = dao.getUserById(id);
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("view/admin/updateuser.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String id_raw = request.getParameter("id");
            int id = Integer.parseInt(id_raw);
            String name = request.getParameter("fullName");
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            Part part = request.getPart("avatar");
            int status = 1;
            String role = request.getParameter("role");

            String realPath = request.getServletContext().getRealPath("images");
            File uploads = new File(realPath);
            if (!uploads.exists()) {
                uploads.mkdirs();
            }

            String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
            String filePath = "assets/images/" + filename;
            UserDAO dao = new UserDAO();
            if (filename.isEmpty()) {

                Users user = dao.getUserById(id);

                filePath = user.getAvatar();

            } else {
                File file = new File(uploads, filename);
                part.write(file.getAbsolutePath());
            }
            if (dao.updateUser(id, name, account, password, email, phone, gender, address, filePath)) {
                request.setAttribute("mess", "Success");
            }

            response.sendRedirect("user");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi xử lý cập nhật: " + e.getMessage());
            request.getRequestDispatcher("view/admin/updateuser.jsp").forward(request, response);
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
