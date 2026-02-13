package com.joaopedro.weatherhub.repository;

import com.joaopedro.weatherhub.model.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long> {

    List<FavoriteCity> findByUserId(Long userId);

    Optional<FavoriteCity> findByIdAndUserId(Long id, Long userId);
}
