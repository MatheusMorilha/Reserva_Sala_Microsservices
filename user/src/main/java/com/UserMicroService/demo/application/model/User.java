package com.UserMicroService.demo.application.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Pattern;


@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Embedded
    private Email email;

    @Embedded
    private Senha senha;

    @Embedded
    private Telefone telefone;

    @Embedded
    private Endereco endereco;

    @Embedded
    private Cpf cpf;

    private LocalDate dataNascimento;
    private LocalDate dataCadastro;
}