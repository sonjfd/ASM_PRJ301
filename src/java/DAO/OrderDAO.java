/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.Cart;
import model.Color;
import model.Item;
import model.OrderDetail;
import model.Orders;
import model.PaymentMethod;
import model.Product;
import model.ProductVariant;
import model.ProductVariantRevenue;
import model.ShippingMethod;
import model.Size;
import model.Users;

/**
 *
 * @author Dell
 */
public class OrderDAO {

    public Orders getOrderById(int orderId) {
        Orders order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                order = new Orders();
                order.setId(rs.getInt(1));
                order.setUsers(new UserDAO().getUserById(rs.getInt(2)));
                order.setTotalQuantity(rs.getInt(3));
                order.setOrderStatusPayment(rs.getInt(4));
                order.setOrderStatusTransport(rs.getInt(5));
                order.setTotal_discount(rs.getInt(6));
                order.setPrice(rs.getInt(7));
                order.setNote(rs.getString(8));
                order.setShipping(new ShippingMethodDAO().getShippingMethodById(rs.getInt(9)));
                order.setPayment(new PaymentMethodDAO().getPaymentMethodById(rs.getInt(10)));
                order.setCreatedAt(rs.getDate(11));
                order.setUpdateAt(rs.getDate(12));

                // Nếu muốn lấy luôn thông tin user
                // (có thể dùng thêm userDAO.getUserById(...) để gán vào order.setUsers(...))
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Orders> searchOrders(String keyword) {
        List<Orders> list = new ArrayList<>();
        // Ở đây ví dụ tìm theo user_id hoặc note hoặc user.name
        // Tuỳ logic mà JOIN bảng users, etc.
        String sql = "SELECT o.* FROM orders o "
                + "JOIN users u ON o.user_id = u.id "
                + "WHERE CAST(o.id as varchar) LIKE ? "
                + "   OR u.name COLLATE Vietnamese_CI_AI LIKE ? "
                + "   OR o.note COLLATE Vietnamese_CI_AI LIKE ? "
                + "ORDER BY o.id DESC";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt(1));
                order.setUsers(new UserDAO().getUserById(rs.getInt(2)));
                order.setTotalQuantity(rs.getInt(3));
                order.setOrderStatusPayment(rs.getInt(4));
                order.setOrderStatusTransport(rs.getInt(5));
                order.setTotal_discount(rs.getInt(6));
                order.setPrice(rs.getInt(7));
                order.setNote(rs.getString(8));
                order.setShipping(new ShippingMethodDAO().getShippingMethodById(rs.getInt(9)));
                order.setPayment(new PaymentMethodDAO().getPaymentMethodById(rs.getInt(10)));
                order.setCreatedAt(rs.getDate(11));
                order.setUpdateAt(rs.getDate(12));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateOrderStatus(int orderId, int paymentStatus, int transportStatus) {
        String sql = "UPDATE orders SET order_status_payment = ?, order_status_transport = ?, updated_at = GETDATE() WHERE id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, paymentStatus);
            ps.setInt(2, transportStatus);
            ps.setInt(3, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM order_detail WHERE order_id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail();

                od.setId(rs.getInt("id"));
                od.setOrder(new OrderDAO().getOrderById(rs.getInt(2)));
                od.setProductVariant(new ProductVariantDAO().getProductVariantById(rs.getInt(3)));
                od.setPrice(rs.getInt("price"));
                od.setQuantity(rs.getInt("quantity"));
                od.setDiscount(rs.getInt("discount"));
                od.setTotalPrice(rs.getInt("total_price"));
                od.setName(rs.getString("name"));
                od.setAvatar(rs.getString("avatar"));
                list.add(od);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Orders> filterOrders(String keyword, Integer paymentStatus, Integer transportStatus, Date fromDate, Date toDate) {
        List<Orders> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT o.* FROM orders o JOIN users u ON o.user_id = u.id WHERE 1=1 ");

        // Nếu có từ khóa tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append("AND (CAST(o.id as varchar) LIKE ? OR u.name COLLATE Vietnamese_CI_AI LIKE ? OR o.note COLLATE Vietnamese_CI_AI LIKE ?) ");
        }
        if (paymentStatus != null) {
            sql.append("AND o.order_status_payment = ? ");
        }
        if (transportStatus != null) {
            sql.append("AND o.order_status_transport = ? ");
        }
        if (fromDate != null) {
            sql.append("AND o.created_at >= ? ");
        }
        if (toDate != null) {
            sql.append("AND o.created_at <= ? ");
        }
        sql.append("ORDER BY o.id DESC");

        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql.toString());
            int index = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
                ps.setString(index++, "%" + keyword + "%");
                ps.setString(index++, "%" + keyword + "%");
            }
            if (paymentStatus != null) {
                ps.setInt(index++, paymentStatus);
            }
            if (transportStatus != null) {
                ps.setInt(index++, transportStatus);
            }
            if (fromDate != null) {
                ps.setTimestamp(index++, new java.sql.Timestamp(fromDate.getTime()));
            }
            if (toDate != null) {
                ps.setTimestamp(index++, new java.sql.Timestamp(toDate.getTime()));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt(1));
                order.setUsers(new UserDAO().getUserById(rs.getInt(2)));
                order.setTotalQuantity(rs.getInt(3));
                order.setOrderStatusPayment(rs.getInt(4));
                order.setOrderStatusTransport(rs.getInt(5));
                order.setTotal_discount(rs.getInt(6));
                order.setPrice(rs.getInt(7));
                order.setNote(rs.getString(8));
                order.setShipping(new ShippingMethodDAO().getShippingMethodById(rs.getInt(9)));
                order.setPayment(new PaymentMethodDAO().getPaymentMethodById(rs.getInt(10)));
                order.setCreatedAt(rs.getDate(11));
                order.setUpdateAt(rs.getDate(12));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

// tạo hóa đơn
    public void addOrder(Users user, Cart cart, String note, ShippingMethod shippingMethod, PaymentMethod paymentMethod) {
        try {
            String sql = "INSERT INTO [dbo].[orders]\n"
                    + "           ([user_id]\n"
                    + "           ,[total_quantity]\n"
                    + "           ,[total_price]\n"
                    + "           ,[note]\n"
                    + "           ,[shipping_method_id]\n"
                    + "           ,[payment_method_id]\n"
                    + "     VALUES(?,?,?,?,?,?)";

            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
//            ps.setInt(2, cart.getTotalQuantity());
            ps.setInt(3, cart.getTotalMoneny());
            ps.setString(4, note);
            ps.setInt(5, shippingMethod.getId());
            ps.setInt(6, paymentMethod.getId());
            ps.executeUpdate();

            // lay id cua order vua add
            String sql2 = "SELECT TOP 1 id FROM orders ORDER BY id desc";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery();
            // add bang orderdetail
            if (rs2.next()) {
                int oid = rs2.getInt("id");
                for (Item item : cart.getItems()) {

                    String sql3 = "INSERT INTO [dbo].[order_detail]\n"
                            + "           ([order_id]\n"
                            + "           ,[product_variant_id]\n"
                            + "           ,[price]\n"
                            + "           ,[quantity]\n"
                            + "           ,[avatar])\n"
                            + "     VALUES(?,?,?,?,?)";
                    PreparedStatement ps3 = conn.prepareStatement(sql3);
                    ps3.setInt(1, oid);
                    ps3.setInt(2, item.getProduct().getId());
//                    ps3.setInt(3, item.getProduct().getPrice());
                    ps3.setInt(4, item.getQuantity());
//                    ps3.setString(5, item.getProduct().);

                    ps3.executeUpdate();
                }
            }
        } catch (Exception e) {
        }
    }

    // Lấy tổng doanh số (trong 7 ngày qua)
    public int getWeeklySales() {
        int weeklySales = 0;
        String sql = "SELECT SUM(total_price) FROM orders WHERE created_at >= DATEADD(DAY, -7, GETDATE())";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                weeklySales = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeklySales;
    }

    // Lấy tổng doanh số của tuần trước (từ ngày -14 đến ngày -7)
    public int getWeeklySalesLastWeek() {
        int weeklySalesLast = 0;
        String sql = "SELECT SUM(total_price) FROM orders WHERE created_at BETWEEN DATEADD(DAY, -14, GETDATE()) AND DATEADD(DAY, -7, GETDATE())";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                weeklySalesLast = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeklySalesLast;
    }

    public int getTotalOrderCountLastWeek() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM orders WHERE created_at BETWEEN DATEADD(DAY, -14, GETDATE()) AND DATEADD(DAY, -7, GETDATE())";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getTotalRevenueLastWeek() {
        int revenueLast = 0;
        String sql = "SELECT SUM(total_price) FROM orders WHERE created_at BETWEEN DATEADD(DAY, -14, GETDATE()) AND DATEADD(DAY, -7, GETDATE())";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                revenueLast = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenueLast;
    }

    // Lấy tổng số đơn hàng
    public int getTotalOrderCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM orders";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    // Lấy tổng doanh thu (từ tất cả các đơn hàng)
    public int getTotalRevenue() {
        int revenue = 0;
        String sql = "SELECT SUM(total_price) FROM orders";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                revenue = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenue;
    }

    // Lấy doanh số theo tháng của năm cho trước (key: tháng, value: tổng doanh thu)
    public Map<Integer, Integer> getMonthlySales(int year) {
        Map<Integer, Integer> monthlySales = new HashMap<>();
        String sql = "SELECT MONTH(created_at) as month, SUM(total_price) as total "
                + "FROM orders WHERE YEAR(created_at)=? GROUP BY MONTH(created_at)";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                int total = rs.getInt("total");
                monthlySales.put(month, total);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthlySales;
    }

    // Lấy doanh thu theo sản phẩm trong Q1 của năm cho trước
    public List<ProductVariantRevenue> getRevenueByProductVariantForPeriod(Date startDate, Date endDate) {
        List<ProductVariantRevenue> list = new ArrayList<>();
        String sql = "SELECT pv.id, pv.product_id, pv.color_id, pv.size_id, pv.price, pv.sale, pv.stock, "
                + "SUM(od.total_price) as revenue "
                + "FROM order_detail od "
                + "JOIN orders o ON od.order_id = o.id "
                + "JOIN product_variants pv ON od.product_variant_id = pv.id "
                + "WHERE o.created_at BETWEEN ? AND ? "
                + "GROUP BY pv.id, pv.product_id, pv.color_id, pv.size_id, pv.price, pv.sale, pv.stock";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            ps.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng ProductVariant
                ProductVariant pv = new ProductVariant();
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                // Lấy đối tượng Color từ ColorDAO
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));

                // Lấy đối tượng Size từ SizeDAO
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                pv.setId(rs.getInt("id"));
                pv.setProduct(product); // giả sử phương thức này có sẵn trong ProductVariant
                pv.setColor(color);
                pv.setSize(size);
                pv.setPrice(rs.getInt("price"));
                pv.setSale(rs.getInt("sale"));
                pv.setStock(rs.getInt("stock"));

                int revenue = rs.getInt("revenue");

                // Tạo đối tượng ProductVariantRevenue
                ProductVariantRevenue pvr = new ProductVariantRevenue(pv, revenue);
                list.add(pvr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy top 5 biến thể sản phẩm có doanh thu cao nhất của năm cho trước
    public List<ProductVariantRevenue> getTopProductVariants(int year) {
        List<ProductVariantRevenue> list = new ArrayList<>();
        String sql = "SELECT TOP 5 pv.id, pv.product_id, pv.color_id, pv.size_id, pv.price, pv.sale, pv.stock, "
                + "SUM(od.total_price) as revenue "
                + "FROM order_detail od "
                + "JOIN orders o ON od.order_id = o.id "
                + "JOIN product_variants pv ON od.product_variant_id = pv.id "
                + "WHERE YEAR(o.created_at)=? "
                + "GROUP BY pv.id, pv.product_id, pv.color_id, pv.size_id, pv.price, pv.sale, pv.stock "
                + "ORDER BY revenue DESC";
        try {
            Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductVariant pv = new ProductVariant();
                Product product = new ProductDAO().getProductById(rs.getInt("product_id"));

                // Lấy đối tượng Color từ ColorDAO
                Color color = new ColorDAO().getColorByid(rs.getInt("color_id"));

                // Lấy đối tượng Size từ SizeDAO
                Size size = new SizeDAO().getSizeById(rs.getInt("size_id"));
                pv.setId(rs.getInt("id"));
                pv.setProduct(product); // giả sử phương thức này có sẵn trong ProductVariant
                pv.setColor(color);
                pv.setSize(size);
                pv.setPrice(rs.getInt("price"));
                pv.setSale(rs.getInt("sale"));
                pv.setStock(rs.getInt("stock"));

                int revenue = rs.getInt("revenue");

                // Sử dụng sẵn DTO ProductVariantRevenue đã có
                ProductVariantRevenue pvr = new ProductVariantRevenue(pv, revenue);
                list.add(pvr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDAO d = new OrderDAO();
        List<Orders> o = d.searchOrders(" ");
        System.out.println(o);
    }
}
