package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MealsCalculator {

    private final FoodService foodService;

    public Map<Food, Integer> calculateMeals(int targetCalories) {
        List<Food> items = foodService.findAll();

        Map<Food, Integer> selectedItems = new HashMap<>();

        int accumulatedCalories = 0;

        while (accumulatedCalories < targetCalories) {
            Collections.shuffle(items);
            Food food = items.get(0);
            accumulatedCalories += food.getCalories();
            selectedItems.merge(food, 1, Integer::sum);
        }

        return selectedItems;
    }
}
