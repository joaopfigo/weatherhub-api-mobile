package com.joaopedro.weatherhub.repository;

import com.joaopedro.weatherhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
