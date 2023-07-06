package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Season;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Service
public class TripTimeCalculator {

    private static final Long KILOMETERS_PER_DAY = 50L;

    public LocalDate calculate(long kilometers, LocalDate startDate) {
        double kilometersWalked = 0.0;
        LocalDate currentDate = startDate;
        Month currentMonth = startDate.getMonth();
        Season currentSeason = Season.getCurrentSeason(currentMonth);
        while (kilometersWalked < kilometers)  {
            Month newMonth = currentDate.getMonth();
            if (!currentMonth.equals(newMonth)) {
                currentSeason = Season.getCurrentSeason(newMonth);
                currentMonth = newMonth;
            }
            kilometersWalked += currentSeason.getSpeedMultiplier() * KILOMETERS_PER_DAY;
            currentDate = currentDate.plusDays(1);
        }
        return currentDate;
    }

    public Set<Season> getAllSeasons(LocalDate startDate, LocalDate endDate) {
        Set<Season> seasons = new HashSet<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            seasons.add(Season.getCurrentSeason(currentDate.getMonth()));
            currentDate = currentDate.plusMonths(1);
        }

        return seasons;
    }
}
