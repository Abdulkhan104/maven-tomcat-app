package com.example.grocery.dao;

import com.example.grocery.model.Gender;
import com.example.grocery.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final List<Product> products = new ArrayList<>();

    static {
        // Men-targeted groceries
        products.add(new Product(1L, "Protein Powder", new BigDecimal("39.99"), Gender.MEN));
        products.add(new Product(2L, "Beef Jerky", new BigDecimal("12.49"), Gender.MEN));
        products.add(new Product(3L, "Energy Drink", new BigDecimal("2.99"), Gender.MEN));
        // Women-targeted groceries
        products.add(new Product(4L, "Organic Green Tea", new BigDecimal("5.99"), Gender.WOMEN));
        products.add(new Product(5L, "Kale Chips", new BigDecimal("4.49"), Gender.WOMEN));
        products.add(new Product(6L, "Probiotic Yogurt", new BigDecimal("3.79"), Gender.WOMEN));
        // Unisex
        products.add(new Product(7L, "Whole Grain Bread", new BigDecimal("3.49"), Gender.UNISEX));
        products.add(new Product(8L, "Free-Range Eggs", new BigDecimal("5.99"), Gender.UNISEX));
    }

    public List<Product> getProductsByGender(Gender gender) {
        if (gender == Gender.UNISEX) {
            return products.stream()
                    .filter(p -> p.getTargetGender() == Gender.UNISEX)
                    .toList();
        }
        return products.stream()
                .filter(p -> p.getTargetGender() == gender || p.getTargetGender() == Gender.UNISEX)
                .toList();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}