package com.project.ticketeria.modules.userBasic;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserBasicRepository extends JpaRepository<UserBasicEntity, UUID> {
    Optional<UserBasicEntity> findByCpf(String cpf);

    Optional<UserBasicEntity> findByEmail(String email);
}
