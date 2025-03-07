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
public class Users {

    private int id;
    private String fullname;
    private String account;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private String address;
    private String avatar;
    private int status;
    private String role;
    private Date createdAt;
    private Date updatedAt;

    public Users() {
    }

    public Users(String fullname, String account, String password, String email, String phone, String gender, String address, String avatar, int status, String role, Date createdAt, Date updatedAt) {
        this.fullname = fullname;
        this.account = account;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.status = status;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Users(int id, String fullname, String account, String password, String email, String phone, String gender, String address, String avatar, int status, String role, Date createdAt, Date updatedAt) {
        this.id = id;
        this.fullname = fullname;
        this.account = account;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.status = status;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Users(String fullname, String account, String password, String email, String phone, String gender, String address) {
        this.fullname = fullname;
        this.account = account;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return "Users{" + "id=" + id + ", fullname=" + fullname + ", account=" + account + ", password=" + password + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", address=" + address + ", avatar=" + avatar + ", status=" + status + ", role=" + role + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
