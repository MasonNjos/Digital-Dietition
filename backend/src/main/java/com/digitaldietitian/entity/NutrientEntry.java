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
    // Required by JPA/Hibernate
    public NutrientEntry() {}

    // Required by NutritionLogService
    public NutrientEntry(String name, Double value, String unit, Double pct) {
    this.name = name;
    this.nutrientValue = value;
    this.unit = unit;
    this.pct = pct;
}

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

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getValue() { return nutrientValue; }
    public void setValue(Double value) { this.nutrientValue = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getPct() { return pct; }
    public void setPct(Double pct) { this.pct = pct; }

     @Override
    public String toString() {
        return name + ": " + nutrientValue + " " + unit + (pct != null ? " (" + pct + "%)" : "");
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nutrientValue, unit, pct);
    }
}