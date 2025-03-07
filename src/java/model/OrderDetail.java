/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */


public class OrderDetail {
    private int id;
    private Orders order; // Đối tượng Order thay vì order_id
    private ProductVariant productVariant; // Đối tượng ProductVariant thay vì product_variant_id
    private int price;
    private int quantity;
    private int discount;
    private int totalPrice;
    private String name;
    private String avatar;

    public OrderDetail() {
    }

    public OrderDetail(int id, Orders order, ProductVariant productVariant, int price, int quantity, int discount, int totalPrice, String name, String avatar) {
        this.id = id;
        this.order = order;
        this.productVariant = productVariant;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.name = name;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "id=" + id + ", order=" + order + ", productVariant=" + productVariant + ", price=" + price + ", quantity=" + quantity + ", discount=" + discount + ", totalPrice=" + totalPrice + ", name=" + name + ", avatar=" + avatar + '}';
    }

   

    
}


