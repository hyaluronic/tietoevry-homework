package com.tietoevry.homework.model;

import lombok.Getter;

import java.time.Month;
import java.util.Arrays;
import java.util.Set;

@Getter
public enum Season {
    WINTER(Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY), 0.7, 1.2),
    SPRING(Set.of(Month.MARCH, Month.APRIL, Month.MAY), 0.9, 1),
    SUMMER(Set.of(Month.JUNE, Month.JULY, Month.AUGUST), 1, 1.2),
    AUTUMN(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER), 0.9, 1);

    private final Set<Month> months;
    private final double speedMultiplier;
    private final double mealsMultiplier;

    Season(Set<Month> months, double speedMultiplier, double mealsMultiplier) {
        this.months = months;
        this.speedMultiplier = speedMultiplier;
        this.mealsMultiplier = mealsMultiplier;
    }

    public static Season getCurrentSeason(Month month) {
        return Arrays.stream(Season.values())
                .filter(season -> season.months.contains(month))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
