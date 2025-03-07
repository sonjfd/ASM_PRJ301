/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.admin;

import DAO.ProductDAO;
import DAO.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Dell
 */@MultipartConfig
 @WebServlet 
public class CreateImg extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateImg</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateImg at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        ProductImageDAO imgDao=new ProductImageDAO();
        int p_id=Integer.parseInt(request.getParameter("pid"));
        String name=request.getParameter("name");
        
        Part part=request.getPart("path");
        String realPath=request.getServletContext().getRealPath("images");
        String filename= Path.of(part.getSubmittedFileName()).getFileName().toString();
        File uploads=new File(realPath);
        if(!uploads.exists()){
            uploads.mkdirs();
        }
        File file=new File(uploads,filename);
        part.write(file.getAbsolutePath());
        Product p=new ProductDAO().getProductById(p_id);
        ProductImage img= new ProductImage( p, name, "/assets/images/" + filename);
        int result=imgDao.insert(img);
        if(result>0){
            response.sendRedirect("img");
        }else{
            request.setAttribute("message", "Thêm ảnh ko thành công");
            request.getRequestDispatcher("/view/admin/listimg.jsp").forward(request, response);
        }
        
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
