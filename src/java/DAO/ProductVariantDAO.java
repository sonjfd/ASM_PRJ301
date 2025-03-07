/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Color;
import model.Product;
import model.ProductVariant;
import model.Size;
import java.sql.Date;
import java.util.List;
import model.Categories;

/**
 *
 * @author ADMIN
 */
public class ProductVariantDAO {

    public ArrayList<ProductVariant> getAll() {
        String sql = "SELECT * FROM product_variants";
        ArrayList<ProductVariant> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product p = (new ProductDAO().getProductById(rs.getInt("product_id")));
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                ProductVariant variant = new ProductVariant(rs.getInt(1), p, size, color, rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDate(9), rs.getDate(10));
                list.add(variant);
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductVariantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ProductVariant getProductVariantById(int id) {
        String sql = "SELECT * FROM product_variants WHERE id = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Lấy đối tượng Product từ ProductDAO
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                // Lấy đối tượng Color từ ColorDAO
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));

                // Lấy đối tượng Size từ SizeDAO
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));

                return new ProductVariant(
                        rs.getInt("id"),
                        product, // Gán đối tượng Product
                        size, // Gán đối tượng Color
                        color, // Gán đối tượng Size
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getInt("stock"),
                        rs.getInt(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductVariantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ProductVariant> getProductVariantByProductId(int pid) throws ClassNotFoundException {
        String sql = "SELECT * FROM product_variants WHERE product_id=?";
        ArrayList<ProductVariant> list = new ArrayList<>();
        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Lấy đối tượng Product từ ProductDAO
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                // Lấy đối tượng Color từ ColorDAO
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));

                // Lấy đối tượng Size từ SizeDAO
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                ProductVariant variant = new ProductVariant(
                        rs.getInt("id"),
                        product, // Gán đối tượng Product
                        size, // Gán đối tượng Color
                        color, // Gán đối tượng Size
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getInt("stock"),
                        rs.getInt(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10)
                );
                list.add(variant);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
//code new 03-06-2025

    public ArrayList<ProductVariant> searchProductVariant(String keyword) throws ClassNotFoundException, SQLException {

        ArrayList<ProductVariant> list = new ArrayList<>();
        String sql = "SELECT * FROM product_variants pv , products p WHERE pv.product_id = p.id and p.name like ?";
        try {
            Connection conn = DBContext.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + keyword.trim() + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));

                list.add(new ProductVariant(
                        rs.getInt("id"),
                        product,
                        size,
                        color,
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getInt("stock"),
                        rs.getInt(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int updateStatus(int variantid, int newStatus) throws ClassNotFoundException {
        String sql = "UPDATE [dbo].[product_variants] SET [status] = ? WHERE id = ?";
        int result = 0;
        try (Connection conn = DBContext.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, newStatus);
            stm.setInt(2, variantid);
            result = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean createMultiProductVariant(String productIds, String colorIds, String sizeIds) throws ClassNotFoundException {
        String sql = "{CALL sp_Create_Product_Variants_Multi(?, ?, ?)}";
        try (Connection conn = DBContext.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, productIds);
            stmt.setString(2, colorIds);
            stmt.setString(3, sizeIds);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int updateVariant(ProductVariant variant) {
        String sql = "UPDATE [dbo].[product_variants]\n"
                + "   SET \n"
                + "      [price] =?,\n"
                + "      [sale] =?,\n"
                + "      [stock] =?,\n"
                + "      [updated_at] =? \n"
                + " WHERE id = ?";

        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);

            // Set parameters for the prepared statement
            stm.setInt(1, variant.getPrice());
            stm.setInt(2, variant.getSale());
            stm.setInt(3, variant.getStock());
            stm.setTimestamp(4, new java.sql.Timestamp(variant.getUpdatedAt().getTime()));
            stm.setInt(5, variant.getId());
            result = stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductVariantDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<ProductVariant> sortDesc() {
        ArrayList<ProductVariant> list = new ArrayList<>();
        String sql = "select * from product_variants\n"
                + "order by price desc";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));

                list.add(new ProductVariant(
                        rs.getInt("id"),
                        product,
                        size,
                        color,
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getInt("stock"),
                        rs.getInt(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<ProductVariant> sortAsc() {
        ArrayList<ProductVariant> list = new ArrayList<>();
        String sql = "select * from product_variants\n"
                + "order by price asc";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));

                list.add(new ProductVariant(
                        rs.getInt("id"),
                        product,
                        size,
                        color,
                        rs.getInt("price"),
                        rs.getInt("sale"),
                        rs.getInt("stock"),
                        rs.getInt(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ProductVariant> filterProductVariants(List<Integer> categoryIds, List<Integer> sizeIds, List<Integer> colorIds, String priceRange) {
        List<ProductVariant> variantList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT pv.* FROM product_variants pv");
        sql.append(" JOIN products p ON pv.product_id = p.id  WHERE 1=1");

        if (!categoryIds.isEmpty()) {
            sql.append(" AND p.category_id IN (");
            for (int i = 0; i < categoryIds.size(); i++) {
                sql.append(categoryIds.get(i));
                if (i < categoryIds.size() - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
        }
        if (!sizeIds.isEmpty()) {
            sql.append(" AND pv.size_id IN (");
            for (int i = 0; i < sizeIds.size(); i++) {
                sql.append(sizeIds.get(i));
                if (i < sizeIds.size() - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
        }
        if (!colorIds.isEmpty()) {
            sql.append(" AND pv.color_id IN (");
            for (int i = 0; i < colorIds.size(); i++) {
                sql.append(colorIds.get(i));
                if (i < colorIds.size() - 1) {
                    sql.append(",");
                }
            }
            sql.append(")");
        }

        if (priceRange != null && !priceRange.isEmpty()) {
            switch (priceRange) {
                case "0-5":
                    sql.append(" AND pv.price BETWEEN 0 AND 5000000");
                    break;
                case "5-10":
                    sql.append(" AND pv.price BETWEEN 5000001 AND 10000000");
                    break;
                case "10+":
                    sql.append(" AND pv.price > 10000000");
                    break;
            }
        }

        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString()); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ProductVariant variant = new ProductVariant();
                Product p = (new ProductDAO().getProductById(rs.getInt("product_id")));
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                variant.setId(rs.getInt("id"));
                variant.setProduct(p);
                variant.setSize(size);
                variant.setColor(color);
                variant.setPrice(rs.getInt("price"));
                variant.setSale(rs.getInt("sale"));
                variant.setStock(rs.getInt("stock"));
                variantList.add(variant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductVariantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return variantList;
    }

    public List<ProductVariant> getTop15HotProductVariants() {
        List<ProductVariant> productVariants = new ArrayList<>();
        String sql = "SELECT TOP 15 pv.*\n"
                + "FROM product_variants pv\n"
                + "JOIN products p ON pv.product_id = p.id\n"
                + "ORDER BY p.total_rating DESC;";

        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductVariant variant = new ProductVariant();
                variant.setId(rs.getInt("id"));
                Product p = (new ProductDAO().getProductById(rs.getInt("product_id")));
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                variant.setId(rs.getInt("id"));
                variant.setProduct(p);
                variant.setSize(size);
                variant.setColor(color);
                variant.setPrice(rs.getInt("price"));
                variant.setSale(rs.getInt("sale"));
                variant.setStock(rs.getInt("stock"));
                variant.setStatus(rs.getInt("status"));
                variant.setCreatedAt(rs.getTimestamp("created_at"));
                variant.setUpdatedAt(rs.getTimestamp("updated_at"));

                productVariants.add(variant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductVariantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productVariants;
    }

    public List<ProductVariant> getTop15SaleProductVariants() {
        List<ProductVariant> productVariants = new ArrayList<>();
        String sql = "select top 15 * from product_variants\n"
                + "order by sale desc";

        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProductVariant variant = new ProductVariant();
                variant.setId(rs.getInt("id"));
                Product p = (new ProductDAO().getProductById(rs.getInt("product_id")));
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                variant.setId(rs.getInt("id"));
                variant.setProduct(p);
                variant.setSize(size);
                variant.setColor(color);
                variant.setPrice(rs.getInt("price"));
                variant.setSale(rs.getInt("sale"));
                variant.setStock(rs.getInt("stock"));
                variant.setStatus(rs.getInt("status"));
                variant.setCreatedAt(rs.getTimestamp("created_at"));
                variant.setUpdatedAt(rs.getTimestamp("updated_at"));

                productVariants.add(variant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductVariantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productVariants;
    }

    public static void main(String[] args) {
        ProductVariantDAO dao = new ProductVariantDAO();
        List<ProductVariant> variants = dao.getTop15SaleProductVariants();
        System.out.println("Danh sách TOP 15 sản phẩm hot:");
        for (ProductVariant variant : variants) {
            System.out.println("ID: " + variant.getId()
                    + ", Tên: " + variant.getProduct().getName()
                    + ", Giá: " + variant.getPrice()
                    + ", Giảm giá: " + variant.getSale()
                    + ", Số lượng còn lại: " + variant.getStock());
        }
    }
}
