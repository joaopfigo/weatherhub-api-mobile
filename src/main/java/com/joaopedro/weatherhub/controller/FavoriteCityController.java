package com.joaopedro.weatherhub.controller;

import com.joaopedro.weatherhub.model.FavoriteCity;
import com.joaopedro.weatherhub.service.FavoriteCityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/favorites")
public class FavoriteCityController {

    private final FavoriteCityService favoriteCityService;

    public FavoriteCityController(FavoriteCityService favoriteCityService) {
        this.favoriteCityService = favoriteCityService;
    }

    @GetMapping
    public List<FavoriteCity> list(@PathVariable Long userId) {
        return favoriteCityService.listByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FavoriteCity add(@PathVariable Long userId, @RequestBody FavoriteCity favoriteCity) {
        return favoriteCityService.add(userId, favoriteCity);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long userId, @PathVariable Long cityId) {
        favoriteCityService.remove(userId, cityId);
    }
}
