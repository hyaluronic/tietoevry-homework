package com.tietoevry.homework.repository;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Misc;
import com.tietoevry.homework.model.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface MiscRepository extends CrudRepository<Misc, Integer> {

    List<Misc> findBySeasonsInAndDayTimesIn(Set<Season> seasons, Set<DayTime> dayTimes);
}
