package com.project.ticketeria.modules.userBasic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketeria.exceptions.UserFoundCPFException;
import com.project.ticketeria.exceptions.UserFoundEmailException;
import com.project.ticketeria.modules.token.useCases.LogoutUserBasicUseCase;
import com.project.ticketeria.modules.userBasic.dtos.AuthUserBasicDTO;
import com.project.ticketeria.modules.userBasic.dtos.RequestUserDTO;
import com.project.ticketeria.modules.userBasic.useCases.AuthenticationUserBasicUseCase;
import com.project.ticketeria.modules.userBasic.useCases.CreateUserBasicUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
public class UserBasicController {
    @Autowired
    private CreateUserBasicUseCase createUserBasicUseCase;

    @Autowired
    private AuthenticationUserBasicUseCase authenticationUserBasicUseCase;

    @Autowired
    private LogoutUserBasicUseCase logoutUserBasicUseCase;

    @PostMapping("/create")
    @Tag(name = "User", description = "Ações e funções do usuário do tipo User")
    @Operation(summary = "Criação do Usuário")
    public ResponseEntity<Object> register(@Valid @RequestBody RequestUserDTO request) {
        try {
            var response = this.createUserBasicUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (UserFoundCPFException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.fillInStackTrace());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Tag(name = "User", description = "Ações e funções do usuário do tipo User")
    @Operation(summary = "Login do Usuário")
    public ResponseEntity<Object> login(@Valid @RequestBody AuthUserBasicDTO request) {
        try {
            var response = this.authenticationUserBasicUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (UserFoundEmailException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // logout
    @PostMapping("/logout")
    @Tag(name = "User", description = "Ações e funções do usuário do tipo User")
    @Operation(summary = "Logout do Usuário")
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            this.logoutUserBasicUseCase.execute(authHeader);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Logout realizado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
