package com.project.ticketeria.modules.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {

    @Query("""
            select t from TokenEntity t
            where t.user.id = :userId and t.loggedOut = false
            """)
    List<TokenEntity> findAllTokensByUser(UUID userId);

    Optional<TokenEntity> findByToken(String token);
}
