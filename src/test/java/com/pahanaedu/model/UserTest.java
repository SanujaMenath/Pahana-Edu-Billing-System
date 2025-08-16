package com.pahanaedu.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        User user = new User(1, "adminUser", "securePass123", User.Role.admin);

        assertEquals(1, user.getId());
        assertEquals("adminUser", user.getUsername());
        assertEquals("securePass123", user.getPassword());
        assertEquals(User.Role.admin, user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User();

        user.setId(2);
        user.setUsername("staffUser");
        user.setPassword("staffPass456");
        user.setRole(User.Role.staff);

        assertEquals(2, user.getId());
        assertEquals("staffUser", user.getUsername());
        assertEquals("staffPass456", user.getPassword());
        assertEquals(User.Role.staff, user.getRole());
    }

    @Test
    public void testChangeRole() {
        User user = new User(3, "multiRoleUser", "pass789", User.Role.staff);

        // Change role to admin
        user.setRole(User.Role.admin);

        assertEquals(User.Role.admin, user.getRole());
    }
}
