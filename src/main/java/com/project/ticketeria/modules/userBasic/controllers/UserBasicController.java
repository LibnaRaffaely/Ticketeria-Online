package com.project.ticketeria.modules.userBasic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketeria.exceptions.UserFoundCPFException;
import com.project.ticketeria.modules.userBasic.dtos.RequestUserDTO;
import com.project.ticketeria.modules.userBasic.useCases.CreateUserBasicUseCase;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
public class UserBasicController {
    @Autowired
    private CreateUserBasicUseCase createUserBasicUseCase;

    @PostMapping("/create")
    @Tag(name = "User", description = "Ações e funções do usuário do tipo User")
    @Operation(summary = "Criação do Usuário")
    public ResponseEntity<Object> register(@Valid @RequestBody RequestUserDTO request) {
        try {
            var response = this.createUserBasicUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (UserFoundCPFException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
