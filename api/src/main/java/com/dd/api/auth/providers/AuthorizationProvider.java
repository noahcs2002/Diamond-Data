package com.dd.api.auth.providers;

import com.dd.api.auth.models.User;

public interface AuthorizationProvider {
    /**
     * Attempt to log in to the service
     * @param username user's Username
     * @param password user's Password
     * @return the user if successful, User.EMPTY_USER()
     */
    User attemptLogin(String username, String password);

    /**
     * Create a new user in the system
     * @param username the username to make
     * @param password the password to make
     * @return the created user
     */
    User createUser(String username, String password);
}