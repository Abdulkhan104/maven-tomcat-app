package com.example.grocery.dao;

import com.example.grocery.model.Gender;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ProductDAOTest {
    private final ProductDAO dao = new ProductDAO();

    @Test
    void getProductsByGender_men_shouldIncludeMenAndUnisex() {
        var products = dao.getProductsByGender(Gender.MEN);
        assertThat(products).allMatch(p -> p.getTargetGender() == Gender.MEN || p.getTargetGender() == Gender.UNISEX);
        assertThat(products).extracting("targetGender").contains(Gender.MEN, Gender.UNISEX);
    }

    @Test
    void getProductsByGender_women_shouldIncludeWomenAndUnisex() {
        var products = dao.getProductsByGender(Gender.WOMEN);
        assertThat(products).allMatch(p -> p.getTargetGender() == Gender.WOMEN || p.getTargetGender() == Gender.UNISEX);
    }

    @Test
    void findById_shouldReturnCorrectProduct() {
        var product = dao.findById(4L);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Organic Green Tea");
    }
}