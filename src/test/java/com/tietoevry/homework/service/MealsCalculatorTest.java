package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class MealsCalculatorTest {

    @Mock
    private FoodService foodService;

    private MealsCalculator mealsCalculator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mealsCalculator = new MealsCalculator(foodService);
    }

    @Test
    public void testCalculateMeals() {
        Food food1 = new Food("Food 1", 200);
        Food food2 = new Food("Food 2", 300);
        Food food3 = new Food("Food 3", 150);
        List<Food> items = Arrays.asList(food1, food2, food3);

        when(foodService.findAll()).thenReturn(items);

        int targetCalories = 500;

        Map<Food, Integer> selectedItems = mealsCalculator.calculateMeals(targetCalories);

        AtomicLong calculatedCalories = new AtomicLong(0);
        selectedItems.forEach((food, count) -> calculatedCalories.updateAndGet(v -> v + food.getCalories() * count));

        assertTrue(calculatedCalories.get() >= targetCalories,
                "calculatedCalories is not greater than or equal to targetCalories");
    }
}
