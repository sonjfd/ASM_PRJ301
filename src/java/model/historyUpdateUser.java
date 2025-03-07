/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Hung
 */
public class historyUpdateUser {
    private int id;
    private int user_id;
    private String field_name;
    private String old_value;
    private String new_value;
    private Date update_at;

    public historyUpdateUser() {
    }

    public historyUpdateUser(int id, int user_id, String field_name, String old_value, String new_value, Date update_at) {
        this.id = id;
        this.user_id = user_id;
        this.field_name = field_name;
        this.old_value = old_value;
        this.new_value = new_value;
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getOld_value() {
        return old_value;
    }

    public void setOld_value(String old_value) {
        this.old_value = old_value;
    }

    public String getNew_value() {
        return new_value;
    }

    public void setNew_value(String new_value) {
        this.new_value = new_value;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "historyUpdateUser{" + "id=" + id + ", user_id=" + user_id + ", field_name=" + field_name + ", old_value=" + old_value + ", new_value=" + new_value + ", update_at=" + update_at + '}';
    }
    
    
}
