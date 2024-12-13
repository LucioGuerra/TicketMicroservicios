package com.tickets.authorization_sv.dto;

import com.tickets.authorization_sv.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @Size(min=3, max=15, message="Username must be between 3 and 15 characters")
    @NotBlank(message="Username is required")
    private String username;

    @Size(min=8, max=30, message="Password must be between 8 and 30 characters")
    @NotBlank(message="Password is required")
    private String password;

    @NotBlank(message="Email is required")
    @Email(message="Email is invalid")
    private String email;

    @NotNull(message="Role is required")
    private Role role;
}
