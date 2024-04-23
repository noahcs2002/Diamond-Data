package com.dd.api.auth.providers;

import com.dd.api.auth.models.User;
import com.dd.api.auth.security.Salt;
import com.dd.api.util.TruncatedSystemTimeProvider;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorizationService {

    private final AuthorizationRepository repository;

    @Autowired
    public AuthorizationService(AuthorizationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User login(String email, String password) {
        String protectedPassword = Salt.applyDoubleEndedSalt(password);
        return this.repository.findAll()
                .stream()
                .filter(p -> p.getEmail().equals(email))
                .filter(p -> p.getPassword().equals(Base64.encodeBase64String(protectedPassword.getBytes())))
                .filter(p -> p.getGhostedDate() == 0)
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public User createUser(User user) {
        // Check that the user isn't already registered
        List<User> control = this.repository.findAll()
                .stream()
                .filter(p -> p.getEmail().equals(user.getEmail()))
                .toList();

        // If account exists, return null
        if (!control.isEmpty()) {
            return null;
        }

        String protectedPassword = Salt.applyDoubleEndedSalt(user.getPassword());
        user.setPassword(Base64.encodeBase64String(protectedPassword.getBytes()));
        this.repository.save(user);
        return user;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        this.repository.findById(id).ifPresent(p -> {
            p.setGhostedDate(new TruncatedSystemTimeProvider().provideTime());
            this.repository.save(p);
        });

        return true;
    }

    @Transactional
    public User getNonTransientUser(User user) {
        return this.repository.findAll()
                .stream()
                .filter(p -> p.transientEqualityCheck(user))
                .toList()
                .stream().findFirst()
                .orElse(null);
    }

    @Transactional
    public User getUser(Long id) {
        return this.repository.findById(id).filter(u -> u.getGhostedDate()==0).orElse(null);
    }

    @Transactional
    public User changeName(Long userId, String name) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(name);

        User user = this.repository.findAll()
                .stream()
                .filter(p -> p.getId().equals(userId))
                .filter(p -> p.getGhostedDate() == 0)
                .findFirst()
                .orElse(null);

        if(user != null) {
            user.setName(name);
            return this.repository.save(user);
        }

        return null;
    }

    public User changePhoneNumber(Long userId, String phoneNumber) {
        Objects.requireNonNull(phoneNumber);
        Objects.requireNonNull(userId);

        User user = this.repository.findAll()
                .stream()
                .filter(p -> p.getId().equals(userId))
                .filter(p -> p.getGhostedDate() == 0)
                .findFirst()
                .orElse(null);

        if (user != null) {
            user.setPhoneNumber(phoneNumber);
            return this.repository.save(user);
        }

        return null;
    }
}