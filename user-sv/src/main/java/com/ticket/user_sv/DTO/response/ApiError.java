package com.ticket.user_sv.DTO.response;

public record ApiError(String code, String message, Integer status) {
}
