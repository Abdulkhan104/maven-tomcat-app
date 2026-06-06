package com.example.grocery.servlet;

import com.example.grocery.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // In a real app you'd process payment, save order, etc.
        req.setAttribute("total", SessionUtil.getCart(req.getSession()).getTotal());
        req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
        // Clear cart after checkout
        SessionUtil.getCart(req.getSession()).clear();
    }
}