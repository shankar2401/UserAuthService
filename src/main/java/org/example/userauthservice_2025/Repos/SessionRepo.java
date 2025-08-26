package org.example.userauthservice_2025.Repos;

import org.example.userauthservice_2025.Models.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<UserSession, Long> {
    Optional<UserSession> findByTokenAndUserId(String token, Long userId);
}
