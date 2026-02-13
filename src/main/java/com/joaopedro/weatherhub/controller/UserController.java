package com.joaopedro.weatherhub.controller;

import com.joaopedro.weatherhub.model.User;
import com.joaopedro.weatherhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController //recebe pedidos HTTP e devolve resposta em JSON
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService; //imutavel

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //sucesso ou nao
    public User criarUsuario(@Valid @RequestBody User user) { //Pega o JSON que veio no corpo da requisição e transforme em objeto Java.
        return userService.criarUsuario(user);
    }

    @GetMapping("/{id}")
    public User buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }
}
