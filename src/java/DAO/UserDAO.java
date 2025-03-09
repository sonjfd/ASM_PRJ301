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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import model.Users;
import model.historyUpdateUser;

public class UserDAO {

    Connection conn;

    public UserDAO() {
        DBContext con = new DBContext();
        try {
            this.conn = con.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Users> getAllUser() {
        String sql = "Select * from Users";
        ArrayList<Users> listUser = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13));
                listUser.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public ArrayList<Users> getUserByName(String name) {
        String sql = "Select * from Users u where u.name COLLATE Latin1_General_CI_AI like '%" + name + "%'";
        ArrayList<Users> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13));
                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Users getUserById(int id) {
        String sql = "Select * from Users u where u.id=  '" + id + "'";
        Users user = new Users();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Users(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("account"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("avatar"),
                        rs.getInt("status"),
                        rs.getString("role"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUser(int id, String name, String account, String password, String email, String phone, String gender, String address, String avatar) {
        String sql = "Update users set name=?,account=?,password=?, email=?,phone=?,gender=?,address=?,avatar=?, updated_at= CURRENT_TIMESTAMP where id=?";
        
        
        try {
           
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, account);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, gender);
            stmt.setString(7, address);
            stmt.setString(8, avatar);
            stmt.setInt(9, id);
            int check = stmt.executeUpdate();
            if (check > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProfile(int id, String name, String account, String email, String phone, String gender, String address, String avatar) {
        String sql = "Update users set name=?,account=?, email=?,phone=?,gender=?,address=?,avatar=?, updated_at= CURRENT_TIMESTAMP where id=?";
        
        
        try {
            
            
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, account);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, gender);
            stmt.setString(6, address);
            stmt.setString(7, avatar);
            stmt.setInt(8, id);
            int check = stmt.executeUpdate();
            if (check > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changePassword(int id, String password) {
        String sql = "update users set password =? where id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, password); 
            stmt.setInt(2, id);          

            int check = stmt.executeUpdate();
            return check > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUser(Users user) {
        String sql = "INSERT INTO users ( name, account, password, email, phone, gender, address, avatar, status, role, created_at, updated_at) "
                + "VALUES (?,?,?,?,?,?,?,?,1,?, CURRENT_TIMESTAMP, NULL)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);


            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getAccount());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getAddress());
            stmt.setString(8, user.getAvatar());
            stmt.setString(9, user.getRole());

            int check = stmt.executeUpdate();
            return check > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUserByRegester(String name, String account, String password, String email, String phone, String gender, String address) throws SQLException {
        String sql = "INSERT INTO users ( name, account, password, email, phone, gender, address, avatar, status, role, created_at, updated_at) "
                + "VALUES (?,?,?,?,?,?,?,null,1,user, CURRENT_TIMESTAMP, NULL)";
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, name);
            stmt.setString(2, account);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, phone);
            stmt.setString(6, gender);
            stmt.setString(7, address);
            int check = stmt.executeUpdate();
            return check > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Users getAccountDuplicate(String account) {
        String sql = "Select*from users where account=?";
        ArrayList<Users> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, account);

            ResultSet rs = stmt.executeQuery();

            Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13));

            return user;

        } catch (SQLException e) {
        }
        return null;
    }

    public boolean toggleStatus(int id, int status) {
        String sql = "UPDATE Users SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Users> filterByStatus(int status) {
        String sql = "Select* from Users where status=?";
        ArrayList<Users> listFilter = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, status);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Users user = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13));
                listFilter.add(user);

            }
            return listFilter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users loginCheck(String account, String password) {
        String sql = "SELECT * FROM Users WHERE account = ? AND password = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, account);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Users(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getInt(10), rs.getString(11), rs.getDate(12), rs.getDate(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<historyUpdateUser> getHistoryUpdateUser(int id){
        String sql ="Select * from user_update_history where user_id=?";
        ArrayList<historyUpdateUser> list = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                historyUpdateUser history = new historyUpdateUser(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6));
                list.add(history);
                
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Users getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn=DBContext.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("name"));
                user.setAccount(rs.getString("account"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                user.setAvatar(rs.getString("avatar"));
                user.setStatus(rs.getInt("status"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setUpdatedAt(rs.getTimestamp("updated_at"));
                
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; // Nếu không tìm thấy người dùng, trả về null
    }

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
//        Users user = new Users("hung","hungdz","123123", "hungzz@gmail.com", "098989898", "nam", "Ha Noi");
//        if(dao.addUserByRegester(user.getFullname(), user.getAccount(), user.getPassword(), user.getAddress(),  user.getPhone(), user.getGender(), user.getAddress())){
//            System.out.println("thanh cong");
//        }
//        System.out.println("khong thanh cong");

        Users user = dao.loginCheck("hunghung", "123456");
        System.out.println(user);

    }
}
