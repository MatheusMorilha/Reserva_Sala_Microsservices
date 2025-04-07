package com.UserMicroService.demo.infrastructure.repository;

import com.UserMicroService.demo.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}