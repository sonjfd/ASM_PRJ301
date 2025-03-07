/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Dell
 */
import java.sql.*;

public class RatingDAO {

  public int insertRating(int productId, int userId, int numberStars, String content) throws SQLException, ClassNotFoundException {
    int result = -1;
    Connection conn = DBContext.getConnection();
    String sql = "INSERT INTO ratings (product_id, user_id, number_stars, content, created_at, updated_at) " +
                 "VALUES (?, ?, ?, ?, GETDATE(), GETDATE())";

    try {
     
        PreparedStatement stm = conn.prepareStatement(sql);


        stm.setInt(1, productId);
        stm.setInt(2, userId);
        stm.setInt(3, numberStars);
        stm.setString(4, content);

        result = stm.executeUpdate();  

    } catch (SQLException e) {
        e.printStackTrace();

    } 

    return result; 
}

    public int getTotalStars(int productId) throws SQLException, ClassNotFoundException {
        int totalStars = 0;
        Connection conn = DBContext.getConnection();
        String sql = "SELECT SUM(number_stars) AS total_stars FROM ratings WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalStars = rs.getInt("total_stars");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return totalStars;
    }

 
    public int getTotalRatings(int productId) throws SQLException, ClassNotFoundException {
        int totalRatings = 0;

       
       Connection conn = DBContext.getConnection();
        String sql = "SELECT COUNT(*) AS total_ratings FROM ratings WHERE product_id = ?";

        try  {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalRatings = rs.getInt("total_ratings");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return totalRatings;
    }

}
