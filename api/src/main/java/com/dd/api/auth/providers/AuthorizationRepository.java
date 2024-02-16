package com.dd.api.auth.providers;

import com.dd.api.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<User, Long> {
}
