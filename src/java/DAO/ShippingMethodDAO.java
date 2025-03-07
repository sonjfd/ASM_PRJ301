/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.ShippingMethod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ShippingMethodDAO {

    public List<ShippingMethod> getAll() throws ClassNotFoundException {
        List<ShippingMethod> list = new ArrayList<>();
        String sql = "SELECT * FROM ShippingMethod";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ShippingMethod sm = new ShippingMethod();
                sm.setId(rs.getInt("id"));
                sm.setName(rs.getString("name"));
                sm.setCost(rs.getInt("cost"));
                sm.setEstimatedTime(rs.getString("estimated_time"));
                sm.setCarrier(rs.getString("carrier"));
                sm.setStatus(rs.getInt("status"));
                list.add(sm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ShippingMethod getShippingMethodById(int id) throws ClassNotFoundException {
        ShippingMethod sm = new ShippingMethod();
        String sql = "SELECT * FROM ShippingMethod WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                sm.setId(rs.getInt("id"));
                sm.setName(rs.getString("name"));
                sm.setCost(rs.getInt("cost"));
                sm.setEstimatedTime(rs.getString("estimated_time"));
                sm.setCarrier(rs.getString("carrier"));
                sm.setStatus(rs.getInt("status"));
                return sm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

