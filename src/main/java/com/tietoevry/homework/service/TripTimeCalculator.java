package com.tietoevry.homework.service;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TripTimeCalculator {

    public static final int KILOMETERS_PER_DAY = 50; //TODO: move to configuration/db

    private final SeasonService seasonService;

    public LocalDate calculateEndDate(long kilometers, LocalDate startDate) {
        double kilometersWalked = 0.0;
        LocalDate currentDate = startDate;
        Month currentMonth = startDate.getMonth();
        Season currentSeason = seasonService.findByMonth(currentMonth);
        while (kilometersWalked < kilometers)  {
            Month newMonth = currentDate.getMonth();
            if (!currentMonth.equals(newMonth)) {
                currentSeason = seasonService.findByMonth(newMonth);
                currentMonth = newMonth;
            }
            kilometersWalked += currentSeason.getSpeedMultiplier() * KILOMETERS_PER_DAY;
            currentDate = currentDate.plusDays(1);
        }
        return currentDate.minusDays(1);
    }

    public Set<Season> getAllTripSeasons(LocalDate startDate, LocalDate endDate) {
        Set<Season> seasons = new HashSet<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            seasons.add(seasonService.findByMonth(currentDate.getMonth()));
            currentDate = currentDate.plusMonths(1).withDayOfMonth(1);
        }

        return seasons;
    }

    public Set<DayTime> getTripDayTimes(LocalDate startDate, LocalDate endDate) {
        Set<DayTime> dayTimes = new HashSet<>();
        dayTimes.add(DayTime.DAY);
        if (endDate.isAfter(startDate)) {
            dayTimes.add(DayTime.NIGHT);
        }
        return dayTimes;
    }
}
