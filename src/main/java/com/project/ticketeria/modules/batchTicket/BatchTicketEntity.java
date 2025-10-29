package com.project.ticketeria.modules.batchTicket;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "batch_ticket")
public class BatchTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private UUID eventId;

    @NotBlank
    private String name;

    @NotBlank
    private Integer totalTickets;
    private Integer availableTickets;

    @NotBlank
    private Double price;
}
