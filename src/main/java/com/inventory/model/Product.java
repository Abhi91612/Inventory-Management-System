package com.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private int stockQuantity;
    private int lowStockThreshold;

    @Transient   
    private String stockStatus;

    public Product() {}

    public Product(String name, String description, int stockQuantity, int lowStockThreshold) {
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.lowStockThreshold = lowStockThreshold;
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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(int lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public String getStockStatus() {
        if (stockQuantity <= 0) {
            return "OUT_OF_STOCK";
        } else if (stockQuantity < lowStockThreshold) {
            return "LOW_STOCK";
        } else {
            return "IN_STOCK";
        }
    }

   
}
