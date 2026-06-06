package com.example.grocery.servlet;

import com.example.grocery.model.Gender;
import com.example.grocery.util.SessionUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductCatalogServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private ProductCatalogServlet servlet;

    @Test
    void doGet_whenGenderInSession_shouldForwardToProductsJsp() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("gender")).thenReturn(Gender.MEN);
        when(request.getRequestDispatcher("/products.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).setAttribute(eq("selectedGender"), eq(Gender.MEN));
        verify(request).setAttribute(eq("products"), any());
        verify(dispatcher).forward(request, response);
        verify(response, never()).sendRedirect(anyString());
    }

    @Test
    void doGet_whenNoGender_shouldRedirectToIndex() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("gender")).thenReturn(null);
        when(request.getContextPath()).thenReturn("");

        servlet.doGet(request, response);

        verify(response).sendRedirect("/");
        verify(request, never()).getRequestDispatcher(anyString());
    }
}