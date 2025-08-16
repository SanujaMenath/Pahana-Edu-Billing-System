package com.pahanaedu.web.servlet;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class LoginServletTest {

    private LoginServlet loginServlet;
    private UserService userService; // mocked
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        loginServlet = new LoginServlet();

        // mock dependencies
        userService = mock(UserService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        // inject mocked UserService using reflection (since init() creates its own)
        loginServlet = new LoginServlet() {
            @Override
            public void init() {
                this.setUserService(userService);
            }
        };
        loginServlet.init();
    }

    @Test
    void testLogin_AdminSuccess() throws Exception {
        // Arrange
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("pass");
        when(request.getSession()).thenReturn(session);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("pass");
        admin.setRole(User.Role.admin);


        when(userService.login("admin", "pass")).thenReturn(admin);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("user", admin);
        verify(response).sendRedirect(request.getContextPath() + "/admin-dashboard.jsp");
    }

    @Test
    void testLogin_Fail_InvalidCredentials() throws Exception {
        // Arrange
        when(request.getParameter("username")).thenReturn("wrong");
        when(request.getParameter("password")).thenReturn("bad");
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        when(userService.login("wrong", "bad")).thenReturn(null);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(request).setAttribute(eq("error"), anyString());
        verify(dispatcher).forward(request, response);
    }
}
