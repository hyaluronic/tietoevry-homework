package com.tietoevry.homework.service;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static com.tietoevry.homework.service.TripTimeCalculator.KILOMETERS_PER_DAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

public class TripTimeCalculatorTest {

    @Mock
    private SeasonService seasonService;

    private TripTimeCalculator tripTimeCalculator;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        tripTimeCalculator = new TripTimeCalculator(seasonService);
    }

    @Test
    public void testCalculateEndDate() {
        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        long kilometers = 200;
        double speedMultiplier = 1.2;
        Season winter = new Season("Winter", Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY), speedMultiplier, 1.2);
        when(seasonService.findByMonth(Month.JANUARY)).thenReturn(winter);

        LocalDate expectedEndDate = startDate
                .plusDays((long) Math.ceil(kilometers / (speedMultiplier * KILOMETERS_PER_DAY) - 1));
        LocalDate calculatedEndDate = tripTimeCalculator.calculateEndDate(kilometers, startDate);

        assertEquals(expectedEndDate, calculatedEndDate);
    }

    @Test
    public void testGetAllTripSeasons() {
        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.MARCH, 31);

        Set<Season> expectedSeasons = new HashSet<>();
        Season winter = new Season("Winter", Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY), 1.2, 1.2);
        Season spring = new Season("Spring", Set.of(Month.MARCH, Month.APRIL, Month.MAY), 0.9, 1);
        expectedSeasons.add(winter);
        expectedSeasons.add(spring);

        when(seasonService.findByMonth(Month.JANUARY)).thenReturn(winter);
        when(seasonService.findByMonth(Month.FEBRUARY)).thenReturn(spring);
        when(seasonService.findByMonth(Month.MARCH)).thenReturn(spring);

        Set<Season> calculatedSeasons = tripTimeCalculator.getAllTripSeasons(startDate, endDate);

        assertIterableEquals(expectedSeasons, calculatedSeasons);
    }

    @Test
    public void testGetTripDayTimes() {
        LocalDate startDate = LocalDate.of(2023, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2023, Month.JANUARY, 3);

        Set<DayTime> expectedDayTimes = new HashSet<>();
        expectedDayTimes.add(DayTime.DAY);
        expectedDayTimes.add(DayTime.NIGHT);

        Set<DayTime> calculatedDayTimes = tripTimeCalculator.getTripDayTimes(startDate, endDate);

        assertEquals(expectedDayTimes, calculatedDayTimes);
    }
}
