package com.drama.drama_user.controller;

import com.drama.drama_user.model.User;
import com.drama.drama_user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User();
        user.setEmail("test@user.com");
        user.setNombre("Test User");
        user.setPassword("123456");

        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(user);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetAllUsers() {
        User user = new User();
        user.setEmail("test@user.com");
        List<User> users = List.of(user);

        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(users, response.getBody());
    }
}
