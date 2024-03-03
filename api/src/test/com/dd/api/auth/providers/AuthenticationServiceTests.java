package com.dd.api.auth.providers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.security.Salt;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class AuthenticationServiceTests {

    private final String email = "control@email.com";
    private final String password = "password";

    @Mock
    private AuthorizationRepository authorizationRepository;

    @InjectMocks
    private AuthorizationService service;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void idealLoginTest() {
        String email = "email@proivder.com";
        String password = "password";
        String encryptedPassword = Base64.encodeBase64String(Salt.applyDoubleEndedSalt(password).getBytes());

        User user = new User();
        user.setEmail(email);
        user.setPassword(encryptedPassword);

        List<User> users = new ArrayList<>();

        users.add(user);

        when(authorizationRepository.findAll()).thenReturn(users);

        User loggedInUser = service.login(email, password);

        assertNotNull(loggedInUser);
        assertEquals(email, loggedInUser.getEmail());
    }

    @Test
    public void loginFailsOnInvalidEmail() {
        String email = "sample@provider.com";
        String password = "password";

        User user = new User();
        user.setPassword("password");
        user.setEmail("other@provider.com");

        List<User> users = new ArrayList<>();
        when(this.authorizationRepository.findAll()).thenReturn(users);

        User loggedInUser = this.service.login(email, password);

        assertNull(loggedInUser);
    }

    @Test
    public void loginFailsOnInvalidPassword() {
        String email = "sample@provider.com";
        String password = "password";

        User user = new User();
        user.setPassword("otherPassword");
        user.setEmail("sample@provider.com");

        List<User> users = new ArrayList<>();
        users.add(user);
        when(this.authorizationRepository.findAll()).thenReturn(users);

        User loggedInUser = this.service.login(email, password);

        assertNull(loggedInUser);
    }

    @Test
    public void idealCreateUserTest() {
        User user = new User();
        String prot = Base64.encodeBase64String(Salt.applyDoubleEndedSalt(password).getBytes());
        user.setEmail(email);
        user.setPassword(prot);

        when(this.authorizationRepository.findAll()).thenReturn(List.of());
        when(this.authorizationRepository.save(user)).thenReturn(user);

        User createdUser = this.service.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user, createdUser);
        verify(authorizationRepository, times(1)).save(any(User.class));
        verify(authorizationRepository, times(1)).findAll();
    }

    @Test
    public void createUserReturnsNullIfUserAlreadyExists() {
        User user = new User(email, password);
        List<User> users = new ArrayList<>();
        users.add(user);

        when(this.authorizationRepository.findAll()).thenReturn(users);
        User loggedInUser = this.service.createUser(user);
        assertNull(loggedInUser);
    }

    @Test
    public void idealDeleteUserTest() {
        User user = new User(email, password);
        when(authorizationRepository.findById(1L)).thenReturn(Optional.of(user));

        boolean success = this.service.deleteUser(1L);
        assertTrue(success);
        verify(authorizationRepository, times(1)).save(any(User.class));
    }

    @Test
    public void loginTestDoesNotCaptureDeletedUsersTest() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(Base64.encodeBase64String(Salt.applyDoubleEndedSalt(password).getBytes()));
        user.setGhostedDate(1L);

        List<User> users = new ArrayList<>();
        users.add(user);
        when(authorizationRepository.findAll()).thenReturn(users);

        User verdict = this.service.login(email, password);
        assertNull(verdict);
    }
}