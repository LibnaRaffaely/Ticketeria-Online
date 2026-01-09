package com.project.ticketeria.modules.token;

import java.time.Instant;

import lombok.Builder;

@Builder
public record TokenDTO(String token, String message, String role, Instant expiresAt) {

}
