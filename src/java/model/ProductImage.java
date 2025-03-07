/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class ProductImage {

    private int id;
    private Product product; // Đối tượng ProductVariant thay vì product_variant_id
    private String name;
    private String path;

    public ProductImage() {
    }

    public ProductImage(int id, Product product, String name, String path) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.path = path;
    }

    public ProductImage(Product product, String name, String path) {
        this.product = product;
        this.name = name;
        this.path = path;
    }
    
    

    

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
