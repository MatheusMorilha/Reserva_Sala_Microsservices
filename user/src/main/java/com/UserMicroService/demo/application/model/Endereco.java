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
class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String cep;
}