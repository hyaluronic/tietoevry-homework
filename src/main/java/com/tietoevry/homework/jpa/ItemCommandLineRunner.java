package com.tietoevry.homework.jpa;

import com.tietoevry.homework.model.Item;
import com.tietoevry.homework.model.Season;
import com.tietoevry.homework.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemCommandLineRunner implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... args) {
        itemRepository.save(Item.builder().name("Umbrella").seasons(List.of(Season.AUTUMN)).build());
        itemRepository.save(Item.builder().name("Coat").seasons(List.of(Season.WINTER, Season.AUTUMN)).build());
        itemRepository.save(Item.builder().name("Sun hat").seasons(List.of(Season.SPRING, Season.SUMMER)).build());
        itemRepository.save(Item.builder().name("Warm hat").seasons(List.of(Season.WINTER, Season.AUTUMN)).build());
        itemRepository.save(Item.builder().name("Shoes").seasons(List.of(Season.WINTER, Season.SPRING, Season.SUMMER, Season.AUTUMN)).build());
    }
}
