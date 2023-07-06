package com.tietoevry.homework.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("MISC")
public class Misc extends Item {

    @ManyToMany
    @JoinTable(
            name = "misc_season",
            joinColumns = @JoinColumn(name = "misc_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id")
    )
    private Set<Season> seasons;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayTime> dayTimes;

    public Misc() {
    }

    public Misc(String name, Set<Season> seasons, Set<DayTime> dayTimes) {
        super(name);
        this.seasons = seasons;
        this.dayTimes = dayTimes;
    }
}
