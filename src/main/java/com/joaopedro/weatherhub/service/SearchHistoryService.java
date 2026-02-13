package com.joaopedro.weatherhub.service;

import com.joaopedro.weatherhub.model.SearchHistory;
import com.joaopedro.weatherhub.repository.SearchHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;

    public SearchHistoryService(SearchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public List<SearchHistory> listarHistoricoPorUsuario(Long userId) {
        return searchHistoryRepository.findByUserId(userId);
    }

    public SearchHistory adicionarBusca(Long userId, SearchHistory history) {
        history.setId(null);// Garante que é um novo registro, não atualiza

        history.setUserId(userId);

        history.setSearchedAt(LocalDateTime.now());

        return searchHistoryRepository.save(history);
    }

    @Transactional
    public void limparHistorico(Long userId) {
        searchHistoryRepository.deleteByUserId(userId);
    }
}
