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
public class Rating {
    private int id;
    private String content;
    private Product product;
    private Users users;
    private int numberStars;
    private Date createdAt;
    private Date updatedAt;

    public Rating() {
    }

    public Rating(int id, String content, Product product, Users users, int numberStars, Date createdAt, Date updatedAt) {
        this.id = id;
        this.content = content;
        this.product = product;
        this.users = users;
        this.numberStars = numberStars;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(int numberStars) {
        this.numberStars = numberStars;
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
        return "Rating{" + "id=" + id + ", content=" + content + ", product=" + product + ", users=" + users + ", numberStars=" + numberStars + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
