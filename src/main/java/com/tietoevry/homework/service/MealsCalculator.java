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

    public Map<Food, Integer> calculateMeals(long targetCalories) {
        List<Food> items = foodService.findAll();

        Map<Food, Integer> selectedItems = new HashMap<>();

        long accumulatedCalories = 0;

        while (accumulatedCalories < targetCalories) {
            Collections.shuffle(items);
            Food food = items.get(0);
            int foodCount = (int) Math.ceil(Math.log(((targetCalories - accumulatedCalories) / food.getCalories()) + 2));
            accumulatedCalories += food.getCalories() * foodCount;
            selectedItems.merge(food, foodCount, Integer::sum);
        }

        return selectedItems;
    }
}
