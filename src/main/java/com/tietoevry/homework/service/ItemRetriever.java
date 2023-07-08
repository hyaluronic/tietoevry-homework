package com.tietoevry.homework.service;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Food;
import com.tietoevry.homework.model.Misc;
import com.tietoevry.homework.model.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemRetriever {

    private final TripTimeCalculator tripTimeCalculator;
    private final CaloriesCalculator caloriesCalculator;
    private final MealsCalculator mealsCalculator;
    private final MiscService miscService;

    public Map<String, Integer> getItems(long kilometers, LocalDate startDate) {
        LocalDate endDate = tripTimeCalculator.calculateEndDate(kilometers, startDate);

        Map<String, Integer> tripItems = new HashMap<>();

        long calories = caloriesCalculator.calculate(startDate, endDate);
        Map<Food, Integer> meals = mealsCalculator.calculateMeals(calories);
        meals.forEach((food, count) -> tripItems.put(food.getName(), count));

        Set<Season> seasons = tripTimeCalculator.getAllTripSeasons(startDate, endDate);
        Set<DayTime> dayTimes = tripTimeCalculator.getTripDayTimes(startDate, endDate);
        List<Misc> seasonalItems = miscService.findBySeasonAndDateTime(seasons, dayTimes);
        seasonalItems.forEach(item -> tripItems.put(item.getName(), 1));

        return tripItems;
    }

}
