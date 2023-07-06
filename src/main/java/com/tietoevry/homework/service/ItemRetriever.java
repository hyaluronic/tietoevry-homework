package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Item;
import com.tietoevry.homework.model.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemRetriever {

    @Autowired
    private TripTimeCalculator tripTimeCalculator;
    @Autowired
    private MealsCalculator mealsCalculator;
    @Autowired
    private ItemService itemService;

//    Item.name, count
    public Map<String, Integer> getItems(long kilometers, LocalDate startDate) {
        LocalDate endDate = tripTimeCalculator.calculate(kilometers, startDate);

        Map<String, Integer> tripItems = new HashMap<>();

        Integer mealsCount = mealsCalculator.calculate(startDate, endDate);
        tripItems.put("Meals", mealsCount);

        //TODO: add night items

        Set<Season> seasons = tripTimeCalculator.getAllSeasons(startDate, endDate);
        List<Item> seasonalItems = itemService.findBySeasons(seasons);
        seasonalItems.forEach(item -> tripItems.put(item.getName(), 1));


        return tripItems;
    }

}
