package com.tietoevry.homework.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("FOOD")
public class Food extends Item {

    private long calories;

    public Food(String name, long calories) {
        super(name);
        this.calories = calories;
    }

    public Food() {
    }
}
