package com.UserMicroService.demo.application.service;

import com.UserMicroService.demo.application.model.User;
import com.UserMicroService.demo.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> listar() {
        return repository.findAll();
    }

    public User salvar(User user) {
        return repository.save(user);
    }

    public Optional<User> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }
}