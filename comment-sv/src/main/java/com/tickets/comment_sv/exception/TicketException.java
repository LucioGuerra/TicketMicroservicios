package com.tickets.comment_sv.exception;

import lombok.Getter;

@Getter
public class TicketException extends RuntimeException {
    private final String code;

    public TicketException(String code, String message) {
        super(message);
        this.code = code;
    }
}
