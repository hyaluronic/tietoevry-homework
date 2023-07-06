package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Food;
import com.tietoevry.homework.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository repository;

    @Cacheable("food")
    public List<Food> findAll() {
        return (List<Food>) repository.findAll();
    }
}
