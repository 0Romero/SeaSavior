package com.example.seasavior.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ClienteCreateDTO {
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Email
    @Size(max = 100)
    private String email;

    // Getters and Setters
}

