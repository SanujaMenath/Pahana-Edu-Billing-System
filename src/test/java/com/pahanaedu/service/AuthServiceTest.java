package com.pahanaedu.service;

import com.pahanaedu.model.User;
import dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private UserDAO userDAOMock;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userDAOMock = Mockito.mock(UserDAO.class);
        authService = new AuthService(userDAOMock);
    }

    @Test
    void login_validCredentials_shouldReturnUser() {
        User mockUser = new User();
        mockUser.setUsername("john");
        mockUser.setPassword("secret");

        when(userDAOMock.findByUsername("john")).thenReturn(mockUser);

        User result = authService.login("john", "secret");

        assertNotNull(result);
        assertEquals("john", result.getUsername());
        verify(userDAOMock, times(1)).findByUsername("john");
    }

    @Test
    void login_invalidPassword_shouldReturnNull() {
        User mockUser = new User();
        mockUser.setUsername("john");
        mockUser.setPassword("secret");

        when(userDAOMock.findByUsername("john")).thenReturn(mockUser);

        User result = authService.login("john", "wrongpass");

        assertNull(result);
        verify(userDAOMock, times(1)).findByUsername("john");
    }

    @Test
    void login_nonExistingUser_shouldReturnNull() {
        when(userDAOMock.findByUsername("unknown")).thenReturn(null);

        User result = authService.login("unknown", "secret");

        assertNull(result);
        verify(userDAOMock, times(1)).findByUsername("unknown");
    }
}
