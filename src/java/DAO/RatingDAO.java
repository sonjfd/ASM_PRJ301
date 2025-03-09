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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.Rating;
import model.Users;

public class RatingDAO {

  public int insertRating(int productId, int userId, int numberStars, String content)  {
    int result = -1;
    Connection conn = null;
    String sql = "INSERT INTO ratings (product_id, user_id, number_stars, content, created_at, updated_at) " +
                 "VALUES (?, ?, ?, ?, GETDATE(), GETDATE())";

    try {
     conn=DBContext.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);


        stm.setInt(1, productId);
        stm.setInt(2, userId);
        stm.setInt(3, numberStars);
        stm.setString(4, content);

        result = stm.executeUpdate();  

    } catch (SQLException e) {
        e.printStackTrace();

    } catch (ClassNotFoundException ex) { 
          Logger.getLogger(RatingDAO.class.getName()).log(Level.SEVERE, null, ex);
      }

    return result; 
}
   public List<Rating> getRatingsByProductId(int productId)  {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT r.id, r.content, r.number_stars, r.created_at, " +
                     "u.id AS user_id, u.name AS user_name, " +
                     "p.id AS product_id, p.name AS product_name " +
                     "FROM ratings r " +
                     "JOIN users u ON r.user_id = u.id " +
                     "JOIN products p ON r.product_id = p.id " +
                     "WHERE r.product_id = ? " +
                     "ORDER BY r.created_at DESC";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, productId);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Product product = new ProductDAO().getProductById(rs.getInt("product_id"));
                    Users user = new UserDAO().getUserById(rs.getInt("user_id"));
                    Rating rating = new Rating(
                        rs.getInt("id"),
                        rs.getString("content"),
                        product,
                        user,
                        rs.getInt("number_stars"),
                        rs.getTimestamp("created_at"),
                        null // Không cần updatedAt
                    );
                    ratings.add(rating);
                }
            }
        } catch (ClassNotFoundException ex) {
          Logger.getLogger(RatingDAO.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(RatingDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
        return ratings;
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
    public static void main(String[] args) {
         RatingDAO dao = new RatingDAO();
        int testProductId = 1;
        List<Rating> ratings = dao.getRatingsByProductId(testProductId);
        
        for (Rating rating : ratings) {
            System.out.println("User: " + rating.getUsers().getFullname());
            System.out.println("Rating: " + rating.getNumberStars() + " stars");
            System.out.println("Content: " + rating.getContent());
            System.out.println("Date: " + rating.getCreatedAt());
            System.out.println("----------------------");
        }
    }

}
