package com.project.ticketeria.modules.token.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutUserBasicUseCase {

    @Autowired
    private JwtUseCase jwtUseCase;

    public void execute(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtUseCase.addTokenToBlacklist(token);
        }
    }
}
