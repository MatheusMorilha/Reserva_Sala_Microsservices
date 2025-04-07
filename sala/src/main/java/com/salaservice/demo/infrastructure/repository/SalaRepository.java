package com.salaservice.demo.infrastructure.repository;

import com.salaservice.demo.application.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<Sala, Long> {}

