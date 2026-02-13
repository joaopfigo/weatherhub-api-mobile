package com.joaopedro.weatherhub.service;

import com.joaopedro.weatherhub.exception.ResourceNotFoundException;
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

    public List<FavoriteCity> listarFavoritosDoUsuario(Long userId) {
        return favoriteCityRepository.findByUserId(userId);
    }

    public FavoriteCity adicionarCidadeFavorita(Long userId, FavoriteCity favoriteCity) {
        favoriteCity.setId(null);// Garante que é um novo registro, não atualiza

        favoriteCity.setUserId(userId);

        favoriteCity.setAddedAt(LocalDateTime.now());

        return favoriteCityRepository.save(favoriteCity);
    }

    public void excluirFavoritoDoUsuario(Long userId, Long cityId) {
        FavoriteCity favorite = favoriteCityRepository.findByIdAndUserId(cityId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Favorito não encontrado para o usuário " + userId + " com id " + cityId
                ));

        favoriteCityRepository.delete(favorite);
    }
}