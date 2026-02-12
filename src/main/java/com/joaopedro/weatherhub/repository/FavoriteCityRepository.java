package com.joaopedro.weatherhub.repository;

import com.joaopedro.weatherhub.model.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {}
