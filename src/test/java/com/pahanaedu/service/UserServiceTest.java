package com.pahanaedu.service;

import com.pahanaedu.model.User;
import dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserDAO userDAO;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDAO = mock(UserDAO.class); // mock the DAO
        userService = new UserService(userDAO); // inject the mock
    }

    @Test
    void testLogin_Success() {
        User mockUser = new User();
        mockUser.setUsername("john");
        mockUser.setPassword("secret");

        when(userDAO.findByUsername("john")).thenReturn(mockUser);

        User result = userService.login("john", "secret");

        assertNotNull(result);
        assertEquals("john", result.getUsername());
        verify(userDAO, times(1)).findByUsername("john");
    }

    @Test
    void testLogin_Fail_WrongPassword() {

        User mockUser = new User();
        mockUser.setUsername("john");
        mockUser.setPassword("secret");

        when(userDAO.findByUsername("john")).thenReturn(mockUser);

        User result = userService.login("john", "wrongpass");

        assertNull(result);
        verify(userDAO, times(1)).findByUsername("john");
    }

    @Test
    void testLogin_Fail_UserNotFound() {

        when(userDAO.findByUsername("unknown")).thenReturn(null);

        User result = userService.login("unknown", "pass");

        assertNull(result);
        verify(userDAO, times(1)).findByUsername("unknown");
    }
}
