package com.example.grocery.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class CartTest {
    private Cart cart;
    private Product product1, product2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product1 = new Product(1L, "Test1", new BigDecimal("10.00"), Gender.MEN);
        product2 = new Product(2L, "Test2", new BigDecimal("5.50"), Gender.WOMEN);
    }

    @Test
    void addItem_shouldIncreaseQuantityWhenSameProductAdded() {
        cart.addItem(product1, 2);
        cart.addItem(product1, 3);
        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getQuantity()).isEqualTo(5);
        assertThat(cart.getTotal()).isEqualTo(new BigDecimal("50.00"));
    }

    @Test
    void removeItem_shouldRemoveProductFromCart() {
        cart.addItem(product1, 1);
        cart.addItem(product2, 1);
        cart.removeItem(1L);
        assertThat(cart.getItems()).hasSize(1);
        assertThat(cart.getItems().get(0).getProduct().getId()).isEqualTo(2L);
    }

    @Test
    void clear_shouldEmptyCart() {
        cart.addItem(product1, 1);
        cart.clear();
        assertThat(cart.getItems()).isEmpty();
        assertThat(cart.getTotal()).isEqualTo(BigDecimal.ZERO);
    }
}