/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.lang.System.Logger;
import java.util.ArrayList;
import model.Categories;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;

/**
 *
 * @author Dell
 */
public class CategoriesDAO {

    public ArrayList<Categories> getAllCategories() {
        ArrayList<Categories> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from [categories]";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getDate(8));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Categories> getCategoriesByName(String name) throws ClassNotFoundException {
        ArrayList<Categories> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from [categories] where name like ?";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            String query = "%" + name + "%";
            stm.setString(1, query);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getDate(8));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Categories getCategoriesByid(int cid) {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "select * from categories where id=?";
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setInt(1, cid);
            rs = stm.executeQuery();
            if (rs.next()) {
                Categories c = new Categories(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getDate(8));
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CategoriesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int insert(Categories category) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        int result = -1;
        String sql = "INSERT INTO [dbo].[categories] "
                + "( [name], [description], [avatar], [status], [hot], [created_at]) "
                + "VALUES ( ?, ?, ?, ?, ?, ?)";

        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);

            
            stm.setString(1, category.getName());
            stm.setString(2, category.getDescription());
            stm.setString(3, category.getAvatar());
            stm.setInt(4, category.getStatus());
            stm.setInt(5, category.getHot());
            stm.setTimestamp(6, new java.sql.Timestamp(category.getCreatedAt().getTime()));

            result = stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public int updateCate(Categories c) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        String sql = "UPDATE [dbo].[categories]\n"
                + "   SET \n"
                + "      [name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[avatar] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[hot] = ?   \n"
                + "      ,[updated_at] = ?\n"
                + " WHERE id=?";
        int result = -1;
        try {
            conn = DBContext.getConnection();
            stm = conn.prepareStatement(sql);
            stm.setString(1, c.getName());
            stm.setString(2, c.getDescription());
            stm.setString(3, c.getAvatar());
            stm.setInt(4, c.getStatus());
            stm.setInt(5, c.getHot());
            stm.setTimestamp(6, new java.sql.Timestamp(c.getCreatedAt().getTime()));
            stm.setInt(7, c.getId());
            result = stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public ArrayList<Categories> filterByStatus(int status) {
        String sql = "Select* from  categories where status=?";
        ArrayList<Categories> listFilter = new ArrayList<>();
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, status);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDate(7), rs.getDate(8));
                listFilter.add(c);

            }
            return listFilter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateStatus(int categoryId, int newStatus) throws ClassNotFoundException {
        String sql = "UPDATE [dbo].[categories] SET [status] = ? WHERE id = ?";
        int result = 0;
        try (Connection conn = DBContext.getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, newStatus);
            stm.setInt(2, categoryId);
            result = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        CategoriesDAO d = new CategoriesDAO();

        ArrayList<Categories>c=d.filterByStatus(0);
        System.out.println(c);

    }
}
