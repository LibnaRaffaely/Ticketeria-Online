package com.project.ticketeria.modules.userBasic.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.project.ticketeria.modules.userBasic.enums.GenderEnum;
import com.project.ticketeria.modules.userBasic.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserDTO {
    private UUID id;
    private String username;

    private String email;

    private String cpf;

    private String phone;

    private String password;

    private String adress;
    private GenderEnum gender;
    private RoleEnum role;
    private LocalDate dateOfBirth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private boolean deleted;
    private Double cash;
}
