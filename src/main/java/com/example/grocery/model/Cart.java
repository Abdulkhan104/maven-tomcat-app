package com.example.grocery.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        Optional<CartItem> existing = items.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();
        if (existing.isPresent()) {
            existing.get().setQuantity(existing.get().getQuantity() + quantity);
        } else {
            items.add(new CartItem(product, quantity));
        }
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public List<CartItem> getItems() { return items; }
    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public void clear() { items.clear(); }
}