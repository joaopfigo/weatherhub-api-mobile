package com.joaopedro.weatherhub.controller;

import com.joaopedro.weatherhub.model.SearchHistory;
import com.joaopedro.weatherhub.service.SearchHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/history")
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    public SearchHistoryController(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    @GetMapping
    public List<SearchHistory> listarHistoricoPorUsuario(@PathVariable Long userId) {
        return searchHistoryService.listarHistoricoPorUsuario(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SearchHistory adicionarBusca(@PathVariable Long userId, @RequestBody SearchHistory history) {
        return searchHistoryService.adicionarBusca(userId, history);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void limparHistorico(@PathVariable Long userId) {
        searchHistoryService.limparHistorico(userId);
    }
}

