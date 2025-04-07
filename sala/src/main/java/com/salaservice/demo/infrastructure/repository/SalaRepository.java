package com.salaservice.demo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salaservice.demo.domain.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {}

