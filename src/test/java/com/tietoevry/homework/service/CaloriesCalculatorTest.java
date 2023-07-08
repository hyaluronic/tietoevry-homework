package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CaloriesCalculatorTest {

    @Mock
    private SeasonService seasonService;

    private CaloriesCalculator caloriesCalculator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        caloriesCalculator = new CaloriesCalculator(seasonService);
    }

    @Test
    public void testCalculate() {
        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.JANUARY, 10);

        Season winter = new Season("Winter", Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY), 0.7, 1.2);
        when(seasonService.findByMonth(Month.JANUARY)).thenReturn(winter);

        long expectedCalories = (long) Math.ceil(winter.getCaloriesMultiplier() * CaloriesCalculator.CALORIES_PER_DAY * 10);
        long calculatedCalories = caloriesCalculator.calculate(startDate, endDate);

        assertEquals(expectedCalories, calculatedCalories);
    }
}

