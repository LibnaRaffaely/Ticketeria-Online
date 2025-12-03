package com.project.ticketeria.exceptions;

public class UserFoundCPFException extends RuntimeException {
    public UserFoundCPFException() {
        super("A user with the provided CPF already exists");
    }
}
