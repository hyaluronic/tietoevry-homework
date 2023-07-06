package com.tietoevry.homework.service;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Misc;
import com.tietoevry.homework.model.Season;
import com.tietoevry.homework.repository.MiscRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MiscService {

    private final MiscRepository repository;

    public List<Misc> findBySeasonAndDateTime(Set<Season> seasons, Set<DayTime> dayTimes) {
        return repository.findBySeasonsInAndDayTimesIn(seasons, dayTimes);
    }
}
