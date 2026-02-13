package com.joaopedro.weatherhub.repository;

import com.joaopedro.weatherhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//Estender JpaRepository<Entidade, Id> faz o Spring implementar métodos CRUD para gerenciar a entidade pela chave primaria

public interface UserRepository extends JpaRepository<User, Long> {}
