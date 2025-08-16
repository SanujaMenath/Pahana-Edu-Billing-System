package com.pahanaedu.web.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class LogoutServletTest {

    private LogoutServlet logoutServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        logoutServlet = new LogoutServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testLogout_ShouldInvalidateSessionAndRedirect() throws IOException {
        // Act
        logoutServlet.doGet(request, response);

        // Assert
        verify(session).invalidate();
        verify(response).sendRedirect("login.jsp");
    }
}
