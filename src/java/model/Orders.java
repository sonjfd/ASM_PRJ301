/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class Orders {

    private int id;
    private Users users;
    private int totalQuantity;
    private int orderStatusPayment;
    private int orderStatusTransport;
    private int total_discount;
    private int price;
    private String note;
    private ShippingMethod shipping;
    private PaymentMethod payment;
    private Date createdAt;
    private Date updateAt;

    public Orders() {
    }

    public Orders(int id, Users users, int totalQuantity, int oderStatusPayment, int orderStatusTransoprt, int total_discount, int price, String note, ShippingMethod shipping, PaymentMethod payment, Date createdAt, Date updateAt) {
        this.id = id;
        this.users = users;
        this.totalQuantity = totalQuantity;
        this.orderStatusPayment = oderStatusPayment;
        this.orderStatusTransport = orderStatusTransoprt;
        this.total_discount = total_discount;
        this.price = price;
        this.note = note;
        this.shipping = shipping;
        this.payment = payment;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public Users getUsers() {
        return users;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public int getOrderStatusPayment() {
        return orderStatusPayment;
    }

    public int getOrderStatusTransport() {
        return orderStatusTransport;
    }

    public int getTotal_discount() {
        return total_discount;
    }

    public int getPrice() {
        return price;
    }

    public String getNote() {
        return note;
    }

    public ShippingMethod getShipping() {
        return shipping;
    }

    public PaymentMethod getPayment() {
        return payment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setOrderStatusPayment(int orderStatusPayment) {
        this.orderStatusPayment = orderStatusPayment;
    }

    public void setOrderStatusTransport(int orderStatusTransport) {
        this.orderStatusTransport = orderStatusTransport;
    }

    public void setTotal_discount(int total_discount) {
        this.total_discount = total_discount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setShipping(ShippingMethod shipping) {
        this.shipping = shipping;
    }

    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", users=" + users + ", totalQuantity=" + totalQuantity + ", orderStatusPayment=" + orderStatusPayment + ", orderStatusTransport=" + orderStatusTransport + ", total_discount=" + total_discount + ", price=" + price + ", note=" + note + ", shipping=" + shipping + ", payment=" + payment + ", createdAt=" + createdAt + ", updateAt=" + updateAt + '}';
    }

    
    

}
