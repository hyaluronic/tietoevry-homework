package com.tietoevry.homework.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.Month;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Month> months;
    private double speedMultiplier;
    private double caloriesMultiplier;

    @ManyToMany(mappedBy = "seasons")
    private Set<Misc> miscs;

    public Season() {
    }

    public Season(String name, Set<Month> months, double speedMultiplier, double caloriesMultiplier) {
        this.name = name;
        this.months = months;
        this.speedMultiplier = speedMultiplier;
        this.caloriesMultiplier = caloriesMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Season season = (Season) o;
        return id != null && Objects.equals(id, season.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
