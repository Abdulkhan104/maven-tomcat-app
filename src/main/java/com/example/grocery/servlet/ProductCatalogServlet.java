package com.example.grocery.servlet;

import com.example.grocery.dao.ProductDAO;
import com.example.grocery.model.Gender;
import com.example.grocery.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductCatalogServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gender gender = SessionUtil.getGender(req.getSession());
        if (gender == null) {
            resp.sendRedirect(req.getContextPath());
            return;
        }
        req.setAttribute("products", productDAO.getProductsByGender(gender));
        req.setAttribute("selectedGender", gender);
        req.getRequestDispatcher("/products.jsp").forward(req, resp);
    }
}