package com.project.ticketeria.modules.userBasic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.project.ticketeria.modules.userBasic.enums.GenderEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_basic")
public class UserBasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^[\\p{L} ]+$", message = "O campo [Name] não deve conter carcteres")
    private String username;

    @NotBlank
    @Email(message = "O campo [Email] deve conter um e-mail válido")
    private String email;

    @NotBlank
    private String cpf;

    private String phone;

    private String password;

    private String adress;

    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean deleted;
    private Double cash;

}
