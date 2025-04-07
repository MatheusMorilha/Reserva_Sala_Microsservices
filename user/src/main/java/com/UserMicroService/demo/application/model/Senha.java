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
class Senha {
    private String senha;

    public void setSenha(String senha) {
        if (senha == null || senha.length() < 8) {
            throw new IllegalArgumentException("Senha inválida. Deve ter pelo menos 8 caracteres.");
        }
        this.senha = senha;
    }
}