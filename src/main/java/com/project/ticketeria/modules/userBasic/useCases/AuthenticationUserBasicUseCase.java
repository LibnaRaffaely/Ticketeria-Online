package com.project.ticketeria.modules.userBasic.useCases;

import com.project.ticketeria.exceptions.UserFoundEmailException;
import com.project.ticketeria.modules.token.TokenDTO;
import com.project.ticketeria.modules.token.TokenEntity;
import com.project.ticketeria.modules.token.TokenRepository;
import com.project.ticketeria.modules.token.useCases.JwtUseCase;
import com.project.ticketeria.modules.userBasic.UserBasicEntity;
import com.project.ticketeria.modules.userBasic.UserBasicRepository;
import com.project.ticketeria.modules.userBasic.dtos.AuthUserBasicDTO;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserBasicUseCase {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserBasicRepository userBasicRepository;

    @Autowired
    private JwtUseCase jwtUseCase;

    @Autowired
    private TokenRepository tokenRepository;

    public TokenDTO execute(AuthUserBasicDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.email(),
                        userDTO.password()));

        UserBasicEntity user = this.userBasicRepository
                .findByEmail(userDTO.email())
                .orElseThrow(() -> new UserFoundEmailException());

        String jwt = jwtUseCase.generateToken(user);
        Instant expiration = Instant.now().plus(24, ChronoUnit.HOURS);

        revokeAllTokensByUser(user);

        // salvar novo token
        TokenEntity tokenSave = new TokenEntity();
        tokenSave.setLoggedOut(false);
        tokenSave.setToken(jwt);
        tokenSave.setUser(user);
        tokenRepository.save(tokenSave);

        return TokenDTO.builder()
                .token(jwt)
                .message("User login was successful")
                .role(user.getRole().name())
                .expiresAt(expiration)
                .build();
    }

    public void revokeAllTokensByUser(UserBasicEntity user) {
        List<TokenEntity> validTokens = tokenRepository.findAllTokensByUser(user.getId());

        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
}
