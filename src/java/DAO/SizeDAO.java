/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import model.Size;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class SizeDAO {
    public Size getSizeById(int id){
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        String sql="select * from  sizes where id=?";
        
        try {
            conn=DBContext.getConnection();
            stm=conn.prepareStatement(sql);
            stm.setInt(1, id);
            rs=stm.executeQuery();
            if (rs.next()) {                
                Size s=new Size(rs.getInt(1), rs.getInt(2));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<Size> getAll(){
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        String sql="select * from sizes\n" +
"\n" +
"order by id";
        ArrayList<Size> list=new ArrayList<>();
        try {
            conn=DBContext.getConnection();
             stm=conn.prepareStatement(sql);
             rs=stm.executeQuery();
             while (rs.next()) {                
                Size s=new Size(rs.getInt(1), rs.getInt(2));
                list.add(s);
            }
            
        } catch (Exception e) {
        }
        return list;
        
    }
    
    public int insert(Size s){
        String sql="INSERT INTO [dbo].[sizes]\n" +
"           ([memory_size])\n" +
"     VALUES\n" +
"           (?)";
        Connection conn=null;
        PreparedStatement stm=null;
        int result=-1;
        try {
            conn=DBContext.getConnection();
            stm=conn.prepareStatement(sql);
            stm.setInt(1, s.getMemorySize());
            result=stm.executeUpdate();
        } catch (SQLException e) {
        
        return result;
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(SizeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static void main(String[] args) {
        SizeDAO d=new SizeDAO();
         Size s=d.getSizeById(1);
         System.out.println(s);
         
    }
}
