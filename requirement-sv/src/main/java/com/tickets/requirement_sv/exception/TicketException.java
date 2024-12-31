package com.tickets.requirement_sv.exception;


public class TicketException extends RuntimeException {
    private String code;

    public TicketException(String code, String message) {
        super(message);
        this.code = code;
    }
}
