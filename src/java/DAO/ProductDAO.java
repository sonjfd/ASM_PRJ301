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

    public ArrayList<Product> getProductsByCategoryId(int cid) {
        ArrayList<Product> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM products WHERE category_id = ?";

        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cid);
            rs = stm.executeQuery();

            while (rs.next()) {
                Categories category = new CategoriesDAO().getCategoriesByid(rs.getInt("category_id"));

                Product product = new Product(
                        rs.getInt("id"),
                        category,
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("avatar"),
                        rs.getInt("status"),
                        rs.getInt("hot"),
                        rs.getInt("total_rating"),
                        rs.getInt("total_stars"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return products;
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

   
    
    
    

    public static void main(String[] args) throws ClassNotFoundException {

        
    }
}
