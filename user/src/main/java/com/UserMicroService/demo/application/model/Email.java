package com.UserMicroService.demo.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.regex.Pattern;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class Email {
    private String email;

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        this.email = email;
    }
}