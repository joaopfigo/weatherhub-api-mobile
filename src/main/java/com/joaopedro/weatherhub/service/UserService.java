package com.joaopedro.weatherhub.service;

import com.joaopedro.weatherhub.exception.ResourceNotFoundException;
import com.joaopedro.weatherhub.model.User;
import com.joaopedro.weatherhub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User criarUsuario(User user) {
        user.setId(null);// Garante que é um novo registro, não atualiza

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User buscarPorId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
    }
}
