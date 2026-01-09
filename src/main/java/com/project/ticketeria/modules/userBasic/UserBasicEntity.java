package com.project.ticketeria.modules.userBasic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.ticketeria.modules.userBasic.enums.GenderEnum;
import com.project.ticketeria.modules.userBasic.enums.RoleEnum;

import jakarta.persistence.Column;
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
public class UserBasicEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^[\\p{L} ]+$", message = "O campo [Name] não deve conter carcteres")
    private String name;

    @NotBlank
    @Email(message = "O campo [Email] deve conter um e-mail válido")
    @Column(unique = true)
    private String email;

    @NotBlank
    private String cpf;

    private String phone;

    private String password;

    private String adress;

    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    @Enumerated(value = EnumType.STRING)
    private RoleEnum role;

    private LocalDate dateOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private boolean deleted;
    private Double cash;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
