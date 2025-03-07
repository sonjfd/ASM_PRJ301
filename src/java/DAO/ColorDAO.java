/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Color;

/**
 *
 * @author Dell
 */
public class ColorDAO {
    public Color getColorByid(int id){
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        String sql="select * from  colors where id=?";
        try {
            conn=DBContext.getConnection();
            stm=conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs=stm.executeQuery();
            if (rs.next()) {                
                Color c=new Color(rs.getInt(1), rs.getString(2));
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }
    public ArrayList<Color> getAll(){
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        String sql="select * from  colors "
                + "order by id";
        ArrayList<Color> list=new ArrayList<>();
        try {
            conn=DBContext.getConnection();
             stm=conn.prepareStatement(sql);
             rs=stm.executeQuery();
             while (rs.next()) {                
                Color c=new Color(rs.getInt(1), rs.getString(2));
                list.add(c);
            }
            
        } catch (Exception e) {
        }
        return list;
        
    }
    
    public int insert(Color color){
        String sql="INSERT INTO [dbo].[colors]\n" +
"           ([color])\n" +
"     VALUES\n" +
"           (?)";
        Connection conn=null;
        PreparedStatement stm=null;
        int result=-1;
        try {
            conn=DBContext.getConnection();
            stm=conn.prepareStatement(sql);
            stm.setString(1, color.getColor());
            result=stm.executeUpdate();
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ColorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
