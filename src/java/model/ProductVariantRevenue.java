/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */


public class ProductVariantRevenue {
    private ProductVariant productVariant;
    private int revenue;

    public ProductVariantRevenue() {
    }

    public ProductVariantRevenue(ProductVariant productVariant, int revenue) {
        this.productVariant = productVariant;
        this.revenue = revenue;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "ProductVariantRevenue{" +
               "productVariant=" + productVariant +
               ", revenue=" + revenue +
               '}';
    }
}

