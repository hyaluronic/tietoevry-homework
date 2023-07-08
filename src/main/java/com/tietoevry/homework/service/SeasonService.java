package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Season;
import com.tietoevry.homework.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Month;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository repository;

    @Cacheable("seasons")
    public Season findByMonth(Month month) {
        return repository
                .findByMonthsContaining(month)
                .stream()
                .findAny()
                .orElseThrow(); //TODO: add normal exception
    }
}
