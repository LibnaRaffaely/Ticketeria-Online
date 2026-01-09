package com.project.ticketeria.modules.token;

import java.util.UUID;

import com.project.ticketeria.modules.userBasic.UserBasicEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserBasicEntity user;
}
