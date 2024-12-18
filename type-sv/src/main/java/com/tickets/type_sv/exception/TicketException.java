package com.tickets.type_sv.exception;

public class TicketException extends RuntimeException {
  private String code;
  public TicketException(String code, String message) {
    super(message);
    this.code = code;
  }
}
