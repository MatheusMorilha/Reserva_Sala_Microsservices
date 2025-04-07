package com.reservaservice.demo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservaservice.demo.domain.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}