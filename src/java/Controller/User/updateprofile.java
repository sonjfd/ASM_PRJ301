/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import model.Users;

/**
 *
 * @author Hung
 */
@MultipartConfig
public class updateprofile extends HttpServlet {

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
            out.println("<title>Servlet updateprofile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateprofile at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("view/common/profile.jsp").forward(request, response);
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
            
            int id = Integer.parseInt((String) request.getParameter("id"));
            String name = request.getParameter("name");
            String account = request.getParameter("account");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            Part part = request.getPart("avatar");
            
            String realPath = request.getServletContext().getRealPath("images");
            File uploads = new File(realPath);
            if (!uploads.exists()) {
                uploads.mkdirs();
            }
            
            String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
            String filePath = "images/" + filename;
            UserDAO dao = new UserDAO();
            
            if (filename.isEmpty()) {
                
                Users user = dao.getUserById(id);
                
                filePath = user.getAvatar();
                
            } else {
                File file = new File(uploads, filename);
                part.write(file.getAbsolutePath());
            }
            
            if (dao.updateProfile(id, name, account, email, phone, gender, address, filePath)) {
                request.setAttribute("mess", "Lưu thành công");
                Users user = dao.getUserById(id);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
            }
            
            request.getRequestDispatcher("view/common/profile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mess", "Lỗi xử lý cập nhật: " + e.getMessage());
            request.getRequestDispatcher("view/common/profile.jsp").forward(request, response);
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
