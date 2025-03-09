/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author Dell
 */
public class Size {
    private int id;
    private int memorySize;

   
    public Size() {
    }

    public Size(int id, int memorySize) {
        this.id = id;
        this.memorySize = memorySize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    @Override
    public String toString() {
        return "Size{" + "id=" + id + ", memorySize=" + memorySize + '}';
    }
    
}
