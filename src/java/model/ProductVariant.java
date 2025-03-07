package model;

import java.util.Date;

public class ProductVariant {

    private int id;
    private Product product; 
private Size size;// Đối tượng Product
    private Color color; // Đối tượng Color
     // Đối tượng Size
    private int price;
    private int sale;
    private int stock;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public ProductVariant() {
    }

    public ProductVariant(int id, Product product, Size size, Color color, int price, int sale, int stock, int status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.product = product;
        this.size = size;
        this.color = color;
        this.price = price;
        this.sale = sale;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductVariant(Product product, Size size, Color color, int price, int sale, int stock, int status, Date createdAt, Date updatedAt) {
        this.product = product;
        this.size = size;
        this.color = color;
        this.price = price;
        this.sale = sale;
        this.stock = stock;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductVariant(int id,int price, int sale, int stock, Date updatedAt) {
        this.id = id;
        this.price = price;
        this.sale = sale;
        this.stock = stock;
        this.updatedAt = updatedAt;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProductVariant{" + "id=" + id + ", product=" + product + ", size=" + size + ", color=" + color + ", price=" + price + ", sale=" + sale + ", stock=" + stock + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

   

    
}
