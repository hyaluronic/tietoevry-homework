package com.tietoevry.homework.repository;

import com.tietoevry.homework.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
