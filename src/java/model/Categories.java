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
public class Categories {
    private int id;
    private String name;
    private String description;
    private String avatar;
    private int status;
    private int hot;
    private Date createdAt;
    private Date updateAt;

    public Categories() {
    }

    public Categories(int id, String name, String description, String avatar, int status, int hot, Date createdAt, Date updateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.status = status;
        this.hot = hot;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Categories(int id, String name, String description, String avatar, int status, int hot, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.status = status;
        this.hot = hot;
        this.createdAt = createdAt;
    }

    public Categories(String name, String description, String avatar, int status, int hot, Date createdAt, Date updateAt) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.status = status;
        this.hot = hot;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
    
    
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Categories{" + "id=" + id + ", name=" + name + ", description=" + description + ", avatar=" + avatar + ", status=" + status + ", hot=" + hot + ", createdAt=" + createdAt + ", updateAt=" + updateAt + '}';
    }
    
    
    
    
}
