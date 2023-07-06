package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class CaloriesCalculator {

    private static final Long CALORIES_PER_DAY = 10000L;  //TODO: move to configuration/db
    //TODO: add algorithm to calculate daily calories by some properties?

    private final SeasonService seasonService;

    public int calculate(LocalDate startDate, LocalDate endDate) {
        double caloriesNeeded = 0;
        LocalDate currentDate = startDate;
        Month currentMonth = startDate.getMonth();
        Season currentSeason = seasonService.findByMonth(currentMonth);
        while (!currentDate.isAfter(endDate))  {
            Month newMonth = currentDate.getMonth();
            if (!currentMonth.equals(newMonth)) {
                currentSeason = seasonService.findByMonth(newMonth);
                currentMonth = newMonth;
            }
            caloriesNeeded += currentSeason.getCaloriesMultiplier() * CALORIES_PER_DAY;
            currentDate = currentDate.plusDays(1);
        }
        return (int) Math.ceil(caloriesNeeded);
    }
}
