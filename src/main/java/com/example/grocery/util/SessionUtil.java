package com.example.grocery.util;

import com.example.grocery.model.Cart;
import com.example.grocery.model.Gender;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    private static final String GENDER_ATTR = "gender";
    private static final String CART_ATTR = "cart";

    public static Gender getGender(HttpSession session) {
        return (Gender) session.getAttribute(GENDER_ATTR);
    }

    public static void setGender(HttpSession session, Gender gender) {
        session.setAttribute(GENDER_ATTR, gender);
    }

    public static Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_ATTR);
        if (cart == null) {
            cart = new Cart();
            session.setAttribute(CART_ATTR, cart);
        }
        return cart;
    }
}