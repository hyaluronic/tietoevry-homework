package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Season;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
public class MealsCalculator {

    private static final Long MEALS_PER_DAY = 5L;

    public Integer calculate(LocalDate startDate, LocalDate endDate) {
        double mealsNeeded = 0;
        LocalDate currentDate = startDate;
        Month currentMonth = startDate.getMonth();
        Season currentSeason = Season.getCurrentSeason(currentMonth);
        while (!currentDate.isAfter(endDate))  {
            Month newMonth = currentDate.getMonth();
            if (!currentMonth.equals(newMonth)) {
                currentSeason = Season.getCurrentSeason(newMonth);
                currentMonth = newMonth;
            }
            mealsNeeded += currentSeason.getMealsMultiplier() * MEALS_PER_DAY;
            currentDate = currentDate.plusDays(1);
        }
        return (int) Math.ceil(mealsNeeded);
    }
}
