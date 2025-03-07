/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Product {
    private int id;
    private Categories category; // Đối tượng Category thay vì category_id
    private String name;
    private String description;
    private String content;
    private String avatar;
    private int status;
    private int hot;
    private int totalRating;
    private int totalStars;
    private Date createdAt;
    private Date updatedAt;
    private ArrayList<ProductVariant> variants;
    
    public Product() {
    }

    public Product(int id, Categories category, String name, String description, String content, String avatar, int status, int hot, int totalRating, int totalStars, Date createdAt, Date updatedAt) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.content = content;
        this.avatar = avatar;
        this.status = status;
        this.hot = hot;
        this.totalRating = totalRating;
        this.totalStars = totalStars;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(Categories category, String name, String description, String content, String avatar, int status, int hot, int totalRating, int totalStars, Date createdAt, Date updatedAt) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.content = content;
        this.avatar = avatar;
        this.status = status;
        this.hot = hot;
        this.totalRating = totalRating;
        this.totalStars = totalStars;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
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

    public ArrayList<ProductVariant> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<ProductVariant> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", category=" + category + ", name=" + name + ", description=" + description + ", content=" + content + ", avatar=" + avatar + ", status=" + status + ", hot=" + hot + ", totalRating=" + totalRating + ", totalStars=" + totalStars + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", variants=" + variants + '}';
    }
    

    
    

   

    

    
    
    
    
    

    

    
    

    
    
    
    
    
    
}
