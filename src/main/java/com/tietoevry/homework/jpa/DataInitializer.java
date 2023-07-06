package com.tietoevry.homework.jpa;

import com.tietoevry.homework.model.DayTime;
import com.tietoevry.homework.model.Food;
import com.tietoevry.homework.model.Misc;
import com.tietoevry.homework.model.Season;
import com.tietoevry.homework.repository.ItemRepository;
import com.tietoevry.homework.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SeasonRepository seasonRepository;
    private final ItemRepository itemRepository;

    @Override
    public void run(String... args){
        Season spring = new Season("Winter", Set.of(Month.DECEMBER, Month.JANUARY, Month.FEBRUARY), 0.7, 1.2);
        Season summer = new Season("Spring", Set.of(Month.MARCH, Month.APRIL, Month.MAY), 0.9, 1);
        Season autumn = new Season("Summer", Set.of(Month.JUNE, Month.JULY, Month.AUGUST), 1, 1.2);
        Season winter = new Season("Autumn", Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER), 0.9, 1);

        seasonRepository.save(spring);
        seasonRepository.save(summer);
        seasonRepository.save(autumn);
        seasonRepository.save(winter);

        itemRepository.save(new Food("coffee", 1));
        itemRepository.save(new Food("Burger", 350));
        itemRepository.save(new Food("Meal bar", 500));
        itemRepository.save(new Food("Chocolate bar", 250));
        itemRepository.save(new Misc("Umbrella", Set.of(autumn), Set.of(DayTime.DAY)));
        itemRepository.save(new Misc("Tent", Set.of(winter, spring, summer, autumn), Set.of(DayTime.NIGHT)));
        itemRepository.save(new Misc("Coat", Set.of(winter, autumn), Set.of(DayTime.DAY)));
        itemRepository.save(new Misc("Sun hat", Set.of(spring, summer), Set.of(DayTime.DAY)));
        itemRepository.save(new Misc("Warm hat", Set.of(winter, autumn), Set.of(DayTime.DAY)));
        itemRepository.save(new Misc("Shoes", Set.of(winter, spring, summer, autumn), Set.of(DayTime.DAY)));
    }
}
