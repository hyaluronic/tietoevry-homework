package com.tietoevry.homework.repository;

import com.tietoevry.homework.model.Item;
import com.tietoevry.homework.model.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findBySeasonsIn(List<Season> seasons);
}
