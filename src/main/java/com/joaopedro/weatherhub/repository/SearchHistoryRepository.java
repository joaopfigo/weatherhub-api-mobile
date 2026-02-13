package com.joaopedro.weatherhub.repository;

import com.joaopedro.weatherhub.model.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    List<SearchHistory> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}