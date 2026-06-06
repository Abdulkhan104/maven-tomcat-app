package com.example.grocery.servlet;

import com.example.grocery.dao.ProductDAO;
import com.example.grocery.model.Product;
import com.example.grocery.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdParam = req.getParameter("productId");
        String quantityParam = req.getParameter("quantity");
        if (productIdParam != null && quantityParam != null) {
            try {
                Long productId = Long.parseLong(productIdParam);
                int quantity = Integer.parseInt(quantityParam);
                Product product = productDAO.findById(productId);
                if (product != null && quantity > 0) {
                    SessionUtil.getCart(req.getSession()).addItem(product, quantity);
                }
            } catch (NumberFormatException ignored) {}
        }
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}