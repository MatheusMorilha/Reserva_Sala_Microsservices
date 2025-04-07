package com.salaservice.demo.interfaces.controller;

import com.salaservice.demo.application.service.SalaService;
import com.salaservice.demo.domain.model.Sala;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService service;

    public SalaController(SalaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sala> listar() {
        return service.listar();
    }

    @PostMapping
    public Sala criar(@RequestBody Sala sala) {
        return service.salvar(sala);
    }
}
