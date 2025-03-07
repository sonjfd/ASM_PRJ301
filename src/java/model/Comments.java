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
public class Comments {

    private int id;
    private String text;
    private Product product;
    private Users users;
    private Date createdAt;
    private Date updateAt;

    public Comments() {
    }

    public Comments(int id, String text, Product product, Users users, Date createdAt, Date updateAt) {
        this.id = id;
        this.text = text;
        this.product = product;
        this.users = users;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Comments{" + "id=" + id + ", text=" + text + ", product=" + product + ", users=" + users + ", createdAt=" + createdAt + ", updateAt=" + updateAt + '}';
    }
    
    

    
}
