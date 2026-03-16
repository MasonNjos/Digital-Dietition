package com.digitaldietitian.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class NutrientEntry {

    @Column(name = "nutrient_name")   // "name" can conflict with reserved words
    private String name;

    @Column(name = "nutrient_value")
    private Double nutrientValue;

    @Column(name = "unit")
    private String unit;

    @Column(name = "pct")
    private Double pct;

    // --- constructors, getters, setters unchanged ---

    // ADD these two — Hibernate needs them for embedded collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutrientEntry)) return false;
        NutrientEntry that = (NutrientEntry) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(nutrientValue, that.nutrientValue) &&
               Objects.equals(unit, that.unit) &&
               Objects.equals(pct, that.pct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nutrientValue, unit, pct);
    }
}