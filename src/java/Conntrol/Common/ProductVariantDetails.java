/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Conntrol.Common;

import DAO.ColorDAO;
import DAO.ProductImageDAO;
import DAO.ProductVariantDAO;
import DAO.RatingDAO;
import DAO.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Color;
import model.ProductImage;
import model.ProductVariant;
import model.Rating;
import model.Size;
import model.Users;

/**
 *
 * @author Dell
 */
public class ProductVariantDetails extends HttpServlet {

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
            out.println("<title>Servlet ProductVariantDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductVariantDetails at " + request.getContextPath() + "</h1>");
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

        String pid_raw = request.getParameter("id");
        String sizeId_raw = request.getParameter("sizeId");
        String colorId_raw = request.getParameter("colorId");

        int productId = Integer.parseInt(pid_raw);

        List<Rating> ratings = new RatingDAO().getRatingsByProductId(productId);
        request.setAttribute("ratings", ratings);

        List<ProductImage> img=new ProductImageDAO().getAllImages();
        request.setAttribute("img", img);
        
        
        ProductVariantDAO variantDao = new ProductVariantDAO();
        ArrayList<ProductVariant> allVariants = variantDao.getProductVariantByProductId(productId);
        List<Size> allSizes = new SizeDAO().getAll();

        Integer sizeId = (sizeId_raw != null) ? Integer.parseInt(sizeId_raw) : null;
        Integer colorId = (colorId_raw != null) ? Integer.parseInt(colorId_raw) : null;

        if (sizeId == null && !allSizes.isEmpty()) {
            sizeId = allSizes.get(0).getId();
        }
        List<Color> availableColors = new ArrayList<>();
        for (ProductVariant variant : allVariants) {
            if (variant.getSize().getId() == sizeId) {
                availableColors.add(variant.getColor());
            }
        }
        if (colorId == null && !availableColors.isEmpty()) {
            colorId = availableColors.get(0).getId();
        }
        ProductVariant selectedVariant = null;
        for (ProductVariant variant : allVariants) {
            if (variant.getSize().getId() == sizeId && variant.getColor().getId() == colorId) {
                selectedVariant = variant;
                break;
            }
        }

        request.setAttribute("listvariant", allVariants);
        request.setAttribute("listsize", allSizes);
        request.setAttribute("listColors", availableColors);
        request.setAttribute("selectedSize", sizeId);
        request.setAttribute("selectedColor", colorId);
        request.setAttribute("selectedVariant", selectedVariant);

        request.getRequestDispatcher("/view/common/Product.jsp").forward(request, response);
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
        int productId = Integer.parseInt(request.getParameter("id"));
        int numberStars = Integer.parseInt(request.getParameter("numberStars"));
        String content = request.getParameter("content");
        RatingDAO ratingDAO = new RatingDAO();
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("redirectAfterLogin", "product-variant-details?id=" + productId);
            response.sendRedirect("login");
            return;
        }
        int result = ratingDAO.insertRating(productId, user.getId(), numberStars, content);

        if (result > 0) {
            response.sendRedirect("product-variant-details?id=" + productId);
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
