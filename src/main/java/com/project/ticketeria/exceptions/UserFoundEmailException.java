package com.project.ticketeria.exceptions;

public class UserFoundEmailException extends RuntimeException {
    public UserFoundEmailException() {
        super("A user with the provided EMAIL already exists");
    }
}
