package com.reservaservice.demo.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservaservice.demo.domain.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {}