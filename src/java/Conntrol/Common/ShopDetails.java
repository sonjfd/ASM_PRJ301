/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Conntrol.Common;

import DAO.CategoriesDAO;
import DAO.ColorDAO;
import DAO.ProductDAO;
import DAO.ProductVariantDAO;
import DAO.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Categories;
import model.Color;
import model.Product;
import model.ProductVariant;
import model.Size;

/**
 *
 * @author Dell
 */
public class ShopDetails extends HttpServlet {

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
            out.println("<title>Servlet ShopDetails</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopDetails at " + request.getContextPath() + "</h1>");
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
        CategoriesDAO cdao = new CategoriesDAO();
        ProductVariantDAO variantdao = new ProductVariantDAO();

        String service = request.getParameter("service");
        List<Size> listsize = new SizeDAO().getAll();
        request.setAttribute("listsize", listsize);
        List<Color> listcolor = new ColorDAO().getAll();
        request.setAttribute("listColors", listcolor);
        List<Categories> listc = cdao.getAllCategories();
        request.setAttribute("listcategori", listc);

        List<ProductVariant> listv;

        if ("sortbyasc".equals(service)) {
            listv = variantdao.sortAsc();
        } else if ("sortbydsc".equals(service)) {
            listv = variantdao.sortDesc();
        } else {
            listv = variantdao.getAll();
        }
        
       
        if ("filterproduct".equals(service)) {
            String[] categoryArray = request.getParameterValues("category[]");
            String[] sizeArray = request.getParameterValues("size[]");
            String[] colorArray = request.getParameterValues("color[]");
            String[] priceArray = request.getParameterValues("price[]");
            List<Integer> categoryIds = getIntegerListFromArray(categoryArray);
            List<Integer> sizeIds = getIntegerListFromArray(sizeArray);
            List<Integer> colorIds = getIntegerListFromArray(colorArray);
            String price = (priceArray != null) ? String.join(",", priceArray) : null;

            
            listv = variantdao.filterProductVariants(categoryIds, sizeIds, colorIds, price);
        }

        int numPerPage = 12;
        int totalVariants = listv.size();
        int totalPages = (int) Math.ceil((double) totalVariants / numPerPage);

        String pageStr = request.getParameter("page");
        int page = (pageStr == null) ? 1 : Integer.parseInt(pageStr);

        int start = (page - 1) * numPerPage;
        int end = Math.min(start + numPerPage, totalVariants);

        List<ProductVariant> paginatedList = listv.subList(start, end);
        request.setAttribute("listvariant", paginatedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/view/common/shop.jsp").forward(request, response);

    }
  

    private List<Integer> getIntegerListFromArray(String[] values) {
        List<Integer> result = new ArrayList<>();
        if (values != null) {
            for (String value : values) {
                try {
                    result.add(Integer.parseInt(value));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
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
