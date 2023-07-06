package com.tietoevry.homework.repository;

import com.tietoevry.homework.model.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
}
