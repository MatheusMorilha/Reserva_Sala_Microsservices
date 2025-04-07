package com.reservaservice.demo.infrastructure.controller;

import com.reservaservice.demo.application.model.Reserva;
import com.reservaservice.demo.application.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        Reserva savedReserva = reservaService.salvar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReserva);
    }
}