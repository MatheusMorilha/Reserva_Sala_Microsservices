package com.reservaservice.demo.interfaces.controller;

import com.reservaservice.demo.application.service.ReservaService;
import com.reservaservice.demo.domain.Reserva;
import com.reservaservice.demo.messaging.ReservaProducer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaProducer reservaProducer;

    public ReservaController(ReservaService reservaService, ReservaProducer reservaProducer) {
        this.reservaService = reservaService;
        this.reservaProducer = reservaProducer;
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @PostMapping
    public ResponseEntity<Reserva> criar(@RequestBody Reserva reserva) {
        Reserva savedReserva = reservaService.salvar(reserva);

        try {
            // Envia mensagem para RabbitMQ notificando a criação da reserva
            String mensagem = "Reserva criada com ID: " + savedReserva.getId();
            reservaProducer.sendReservaMessage(mensagem);
        } catch (Exception e) {
            // Log do erro, mas não impede a criação da reserva
            System.err.println("Erro ao enviar mensagem RabbitMQ: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReserva);
    }
}
