package com.tietoevry.homework.service;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Food;
import com.tietoevry.homework.model.Misc;
import com.tietoevry.homework.model.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ItemRetrieverTest {

    @Mock
    private TripTimeCalculator tripTimeCalculator;

    @Mock
    private CaloriesCalculator caloriesCalculator;

    @Mock
    private MealsCalculator mealsCalculator;

    @Mock
    private MiscService miscService;

    private ItemRetriever itemRetriever;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        itemRetriever = new ItemRetriever(tripTimeCalculator, caloriesCalculator, mealsCalculator, miscService);
    }

    @Test
    public void testGetItems() {
        long kilometers = 100;
        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.JANUARY, 5);

        when(tripTimeCalculator.calculateEndDate(kilometers, startDate)).thenReturn(endDate);

        long calories = 500;
        when(caloriesCalculator.calculate(startDate, endDate)).thenReturn(calories);

        Map<Food, Integer> meals = new HashMap<>();
        meals.put(new Food("Food 1", 200), 2);
        meals.put(new Food("Food 2", 300), 1);
        when(mealsCalculator.calculateMeals(calories)).thenReturn(meals);

        Set<Season> seasons = Set.of(new Season("Winter", Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY), 0.7, 1.2));
        Set<DayTime> dayTimes = Set.of(DayTime.DAY);
        List<Misc> seasonalItems = List.of(new Misc("Item 1", seasons, dayTimes));
        when(tripTimeCalculator.getAllTripSeasons(startDate, endDate)).thenReturn(seasons);
        when(tripTimeCalculator.getTripDayTimes(startDate, endDate)).thenReturn(dayTimes);
        when(miscService.findBySeasonAndDateTime(seasons, dayTimes)).thenReturn(seasonalItems);

        Map<String, Integer> result = itemRetriever.getItems(kilometers, startDate);

        assertEquals(3, result.size());
        assertEquals(2, result.get("Food 1"));
        assertEquals(1, result.get("Food 2"));
        assertEquals(1, result.get("Item 1"));
    }
}
