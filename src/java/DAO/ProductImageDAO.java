/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import model.ProductImage;
import java.sql.*;
import java.util.logging.Level;
import model.Product;
import model.ProductVariant;

/**
 *
 * @author Dell
 */
public class ProductImageDAO {

    public List<ProductImage> getImagesByProductVariantId(int productId) throws ClassNotFoundException {
        List<ProductImage> images = new ArrayList<>();
        String sql = "SELECT * FROM product_images WHERE product_variant_id = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Lấy đối tượng ProductVariant từ ProductVariantDAO
                Product product = new ProductDAO().getProductById(productId);

                ProductImage image = new ProductImage(
                        rs.getInt("id"),
                        product, // Gán đối tượng ProductVariant
                        rs.getString("name"),
                        rs.getString("path")
                );
                images.add(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public List<ProductImage> getAllImages() {
        List<ProductImage> list = new ArrayList<>();
        String sql = "select * from product_images";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product p = (new ProductDAO()).getProductById(rs.getInt("product_id"));
                ProductImage img = new ProductImage(rs.getInt(1), p, rs.getString(3), rs.getString(4));
                list.add(img);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public int insert(ProductImage img) {
        String sql = "INSERT INTO [dbo].[product_images] "
                + "([product_id], [name], [path]) "
                + "VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stm = null;
        int result = -1;
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);

            stm.setInt(1, img.getProduct().getId());
            stm.setString(2, img.getName());
            stm.setString(3, img.getPath());
            result = stm.executeUpdate();
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProductImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        ProductImageDAO dao = new ProductImageDAO();
        List<ProductImage> list = dao.getAllImages();
        System.out.println(list);
    }

}
