package com.tietoevry.homework.service;

import com.tietoevry.homework.model.Item;
import com.tietoevry.homework.model.Season;
import com.tietoevry.homework.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return (List<Item>) repository.findAll();
    }

    public List<Item> findBySeasons(Set<Season> seasons) {
        return repository.findBySeasonsIn(new ArrayList<>(seasons));
    }

    public Optional<Item> findOptionalById(int id) {
        return repository.findById(id);
    }

    public Item update(Item item) {
        return repository.save(item);
    }

    public Item add(Item item) {
        return repository.save(item);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
