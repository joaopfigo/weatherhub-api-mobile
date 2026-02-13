package com.joaopedro.weatherhub.service;

import com.joaopedro.weatherhub.model.FavoriteCity;
import com.joaopedro.weatherhub.repository.FavoriteCityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteCityService {

    private final FavoriteCityRepository favoriteCityRepository;

    public FavoriteCityService(FavoriteCityRepository favoriteCityRepository) {
        this.favoriteCityRepository = favoriteCityRepository;
    }

    public List<FavoriteCity> listByUserId(Long userId) {
        return favoriteCityRepository.findByUserId(userId);
    }

    public FavoriteCity add(Long userId, FavoriteCity favoriteCity) {
        favoriteCity.setId(null);

        favoriteCity.setUserId(userId);

        favoriteCity.setAddedAt(LocalDateTime.now());

        return favoriteCityRepository.save(favoriteCity);
    }

    public void remove(Long userId, Long cityId) {
        FavoriteCity favorite = favoriteCityRepository.findByIdAndUserId(cityId, userId)
                .orElseThrow(() -> new RuntimeException(
                        "Favorito não encontrado para o usuário " + userId + " com id " + cityId
                ));

        favoriteCityRepository.delete(favorite);
    }
}