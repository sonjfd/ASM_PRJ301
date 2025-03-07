/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import model.PaymentMethod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Dell
 */
public class PaymentMethodDAO {

    public List<PaymentMethod> getAll() {
        List<PaymentMethod> list = new ArrayList<>();
        String sql = "SELECT * FROM PaymentMethod";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PaymentMethod pm = new PaymentMethod();
                pm.setId(rs.getInt("id"));
                pm.setName(rs.getString("name"));
                pm.setDescription(rs.getString("description"));
                pm.setStatus(rs.getInt("status"));
                list.add(pm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public PaymentMethod getPaymentMethodById(int id) {
        PaymentMethod pm = new PaymentMethod();
        String sql = "SELECT * FROM PaymentMethod WHERE id = ?";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                pm.setId(rs.getInt("id"));
                pm.setName(rs.getString("name"));
                pm.setDescription(rs.getString("description"));
                pm.setStatus(rs.getInt("status"));
                return pm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
