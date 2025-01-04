package com.tickets.requirement_sv.dto;

public record ApiError(String error, String message, Integer status) {
}
