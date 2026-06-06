package com.example.grocery.servlet;

import com.example.grocery.model.Gender;
import com.example.grocery.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/set-gender")
public class SetGenderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String genderParam = req.getParameter("gender");
        if (genderParam != null) {
            Gender gender = Gender.valueOf(genderParam.toUpperCase());
            SessionUtil.setGender(req.getSession(), gender);
        }
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}