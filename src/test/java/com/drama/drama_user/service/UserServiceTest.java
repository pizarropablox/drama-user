package com.drama.drama_user.service;

import com.drama.drama_user.model.Role;
import com.drama.drama_user.model.User;
import com.drama.drama_user.repository.RoleRepository;
import com.drama.drama_user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    // Inicialización de mocks
    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        // Configuración
        User user = new User();
        user.setEmail("test@user.com");
        user.setNombre("Test User");
        user.setPassword("123456");
        user.setRoles(Set.of(new Role("USER")));

        // Mocks
        when(roleRepository.findByName("USER")).thenReturn(Optional.empty());
        when(roleRepository.save(any())).thenReturn(new Role("USER"));
        when(passwordEncoder.encode("123456")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Ejecución
        User result = userService.createUser(user);

        // Validaciones
        assertNotNull(result);
        assertEquals("test@user.com", result.getEmail());
        assertEquals("Test User", result.getNombre());
        assertEquals("encodedPassword", result.getPassword());
    }

    @Test
    void testFindByEmail_UserExists() {
        // Configuración
        User user = new User();
        user.setEmail("test@user.com");

        when(userRepository.findByEmail("test@user.com")).thenReturn(Optional.of(user));

        // Ejecución
        User result = userService.findByEmail("test@user.com");

        // Validaciones
        assertNotNull(result);
        assertEquals("test@user.com", result.getEmail());
    }

    @Test
    void testFindByEmail_UserDoesNotExist() {
        // Configuración
        when(userRepository.findByEmail("nonexistent@user.com")).thenReturn(Optional.empty());

        // Ejecución y validación de excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.findByEmail("nonexistent@user.com");
        });

        assertEquals("Usuario no encontrado con el email: nonexistent@user.com", exception.getMessage());
    }
}
