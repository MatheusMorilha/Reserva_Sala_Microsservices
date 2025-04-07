package com.UserMicroService.demo.infrastructure.controller;

import com.UserMicroService.demo.application.model.User;
import com.UserMicroService.demo.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> listar() {
        return service.listar();
    }

    @PostMapping("/salvar")
    public User salvar(@RequestBody User user) {
        return service.salvar(user);
    }

}
