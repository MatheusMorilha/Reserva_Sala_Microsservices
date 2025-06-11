package com.reservaservice.demo.application.service;


import com.reservaservice.demo.domain.Reserva;
import com.reservaservice.demo.infrastructure.ReservaRepository;
import com.reservaservice.demo.messaging.ReservaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ReservaProducer reservaProducer;

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva salvar(Reserva reserva) {
        Reserva saved = repository.save(reserva);

        String mensagem = "Reserva criada com ID: " + saved.getId();
     
        reservaProducer.sendReservaMessage(mensagem);

        return saved;
    }
}


