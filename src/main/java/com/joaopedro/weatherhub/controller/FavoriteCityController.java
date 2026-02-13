package com.joaopedro.weatherhub.controller;

import com.joaopedro.weatherhub.model.FavoriteCity;
import com.joaopedro.weatherhub.service.FavoriteCityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/favorites")
public class FavoriteCityController {
    //@PathVariable = Indica que o valor do parâmetro userId será extraído da URL da requisição.
    //@RequestBody = Indica que o corpo da requisição deve ser convertido em um objeto FavoriteCity.
    private final FavoriteCityService favoriteCityService;

    public FavoriteCityController(FavoriteCityService favoriteCityService) {
        this.favoriteCityService = favoriteCityService;
    }

    @GetMapping
    public List<FavoriteCity> listarFavoritosDoUsuario(@PathVariable Long userId) {
        return favoriteCityService.listarFavoritosDoUsuario(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavoriteCity adicionarCidadeFavorita(@PathVariable Long userId, @RequestBody FavoriteCity favoriteCity) {
        return favoriteCityService.adicionarCidadeFavorita(userId, favoriteCity);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirFavoritoDoUsuario(@PathVariable Long userId, @PathVariable Long cityId) {
        favoriteCityService.excluirFavoritoDoUsuario(userId, cityId);
    }
}
