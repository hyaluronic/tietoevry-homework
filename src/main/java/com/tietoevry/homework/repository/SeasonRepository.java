package com.tietoevry.homework.repository;

import com.tietoevry.homework.model.Season;
import org.springframework.data.repository.CrudRepository;

import java.time.Month;
import java.util.List;

public interface SeasonRepository extends CrudRepository<Season, Integer> {

    List<Season> findByMonthsContaining(Month month);
}
