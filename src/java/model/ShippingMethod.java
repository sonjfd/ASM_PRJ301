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
public class ShippingMethod {
    private int id;
    private String name;
    private int cost;
    private String estimatedTime;
    private String carrier;
    private int status;

    public ShippingMethod() {
    }

    
    
    public ShippingMethod(int id, String name, int cost, String estimatedTime, String carrier, int status) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.estimatedTime = estimatedTime;
        this.carrier = carrier;
        this.status = status;
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

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   

    @Override
    public String toString() {
        return "ShippingMethod{" + "id=" + id + ", name=" + name + ", cost=" + cost + ", estimatedTime=" + estimatedTime + ", carrier=" + carrier + ", status=" + status + '}';
    }
    
    
}
