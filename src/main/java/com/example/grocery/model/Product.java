package com.example.grocery.model;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Gender targetGender;

    public Product(Long id, String name, BigDecimal price, Gender targetGender) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.targetGender = targetGender;
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public Gender getTargetGender() { return targetGender; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
}