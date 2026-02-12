package com.joaopedro.weatherhub.controller;

import com.joaopedro.weatherhub.model.User;
import com.joaopedro.weatherhub.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController //recebe pedidos HTTP e devolve resposta em JSON
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) { //os dados do JSON que vieram do pedido devem virar um objeto
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
