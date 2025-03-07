/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categories;
import model.Color;
import model.ProductVariant;
import model.Size;

/**
 *
 * @author Dell
 */
public class ProductDAO {

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from products";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories c = (new CategoriesDAO().getCategoriesByid(rs.getInt("category_id")));

                list.add(new Product(
                        rs.getInt("id"),
                        c, // Gán đối tượng Category thay vì ID
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("avatar"),
                        rs.getInt("status"),
                        rs.getInt("hot"),
                        rs.getInt("total_rating"),
                        rs.getInt("total_stars"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Product> searchProductByName(String name) throws ClassNotFoundException {
        ArrayList<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from products where name like ?";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            String query = "%" + name.trim() + "%";
            stm.setString(1, query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories c = (new CategoriesDAO().getCategoriesByid(rs.getInt("category_id")));

                list.add(new Product(
                        rs.getInt("id"),
                        c, // Gán đối tượng Category thay vì ID
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("avatar"),
                        rs.getInt("status"),
                        rs.getInt("hot"),
                        rs.getInt("total_rating"),
                        rs.getInt("total_stars"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductById(int id) {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from products where id=?";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Categories c = (new CategoriesDAO().getCategoriesByid(rs.getInt("category_id")));

                return new Product(
                        rs.getInt("id"),
                        c, // Gán đối tượng Category thay vì ID
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("avatar"),
                        rs.getInt("status"),
                        rs.getInt("hot"),
                        rs.getInt("total_rating"),
                        rs.getInt("total_stars"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public int updateStatus(int productid, int newStatus) throws ClassNotFoundException {
        String sql = "UPDATE [dbo].[products] SET [status] = ? WHERE id = ?";
        int result = 0;
        try (Connection conn = DBContext.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, newStatus);
            stm.setInt(2, productid);
            result = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insertProduct(Product p) {
        Connection conn = null;
        PreparedStatement stm = null;
        int result = -1;
        String sql = "INSERT INTO [dbo].[products] "
                + "([category_id], [name], [description], [content], [avatar], [created_at]) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, p.getCategory().getId());
            stm.setString(2, p.getName());
            stm.setString(3, p.getDescription());
            stm.setString(4, p.getContent());
            stm.setString(5, p.getAvatar());
            stm.setTimestamp(6, new java.sql.Timestamp(p.getCreatedAt().getTime()));

            result = stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int updateProduct(Product p) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [category_id] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[content] = ?\n"
                + "      ,[avatar] = ?\n"
                + "           ,[status]=?\n"
                + "           ,[hot]=?\n"
                + "      ,[updated_at] = ?\n"
                + " WHERE id=?";
        Connection conn = null;
        PreparedStatement stm = null;
        int result = -1;
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, p.getCategory().getId());
            stm.setString(2, p.getName());
            stm.setString(3, p.getDescription());
            stm.setString(4, p.getContent());
            stm.setString(5, p.getAvatar());
            stm.setInt(6, p.getStatus());
            stm.setInt(7, p.getHot());
            stm.setTimestamp(8, new java.sql.Timestamp(p.getUpdatedAt().getTime()));
            stm.setInt(9, p.getId());
            result = stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<Product> FilterProduct(int cid, Date startDate, Date endDate) {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT [id], [category_id], [name], [description], [content], [avatar], [status], [hot], "
                + "[total_rating], [total_stars], [created_at], [updated_at] "
                + "FROM [dbo].[products] "
                + "WHERE 1=1";

        if (cid != 0) {
            sql += " and category_id = ?";
        }

        if (startDate != null && endDate != null) {
            sql += " and updated_at between ? and ?";
        }

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);

            int index = 1;
            if (cid != 0) {
                stm.setInt(index++, cid);
            }

            if (startDate != null && endDate != null) {
                stm.setDate(index++, new java.sql.Date(startDate.getTime()));
                stm.setDate(index++, new java.sql.Date(endDate.getTime()));
            }

            rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                Categories c = (new CategoriesDAO()).getCategoriesByid(rs.getInt("category_id"));
                p.setCategory(c);
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));
                p.setContent(rs.getString("content"));
                p.setAvatar(rs.getString("avatar"));
                p.setStatus(rs.getInt("status"));
                p.setHot(rs.getInt("hot"));
                p.setTotalRating(rs.getInt("total_rating"));
                p.setTotalStars(rs.getInt("total_stars"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                p.setUpdatedAt(rs.getTimestamp("updated_at"));

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Product> getTop10HotProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT TOP(10)"
                + "    p.id AS product_id, "
                + "    p.category_id as category_id, "
                + "    p.name AS name, "
                + "    p.description AS description, "
                + "    p.content AS content, "
                + "    p.avatar AS avatar, "
                + "    p.total_rating, "
                + "    p.total_stars, "
                + "    pv.id AS variant_id, "
                + "    pv.size_id, "
                + "    pv.color_id, "
                + "    pv.price, "
                + "    pv.sale, "
                + "    pv.stock, "
                + "    pv.status AS variant_status "
                + "FROM products p "
                + "JOIN product_variants pv ON p.id = pv.product_id "
                + "WHERE p.status = 1 AND pv.status = 1 "
                + "ORDER BY p.total_stars DESC ";

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            Map<Integer, Product> productMap = new HashMap<>();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Product product = productMap.get(productId);
                if (product == null) {
                    product = new Product();
                    product.setId(productId);
                    Categories c = (new CategoriesDAO()).getCategoriesByid(rs.getInt("category_id"));
                    product.setCategory(c);
                    product.setName(rs.getString("name"));
                    product.setDescription(rs.getString("description"));
                    product.setContent(rs.getString("content"));
                    product.setAvatar(rs.getString("avatar"));
                    product.setTotalRating(rs.getInt("total_rating"));
                    product.setTotalStars(rs.getInt("total_stars"));
                    product.setVariants(new ArrayList<>());  // Khởi tạo danh sách biến thể
                    productMap.put(productId, product);
                }

                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                ProductVariant variant = new ProductVariant();
                variant.setId(rs.getInt("variant_id"));

                variant.setSize(size);
                variant.setColor(color);
                variant.setPrice(rs.getInt("price"));
                variant.setSale(rs.getInt("sale"));
                variant.setStock(rs.getInt("stock"));
                variant.setStatus(rs.getInt("variant_status"));

                product.getVariants().add(variant);
            }
            products.addAll(productMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    public int getTotalProducts() {
        String query = "SELECT COUNT(*) FROM Product";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> getProductsByPage(int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product LIMIT ? OFFSET ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, (page - 1) * pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categories c = (new CategoriesDAO().getCategoriesByid(rs.getInt("category_id")));
                list.add(new Product(
                        rs.getInt("id"),
                        c, // Gán đối tượng Category thay vì ID
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("avatar"),
                        rs.getInt("status"),
                        rs.getInt("hot"),
                        rs.getInt("total_rating"),
                        rs.getInt("total_stars"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> li = (ArrayList<Product>) productDAO.getTop10HotProducts();
        System.out.println(li);
    }
}
