package com.digitaldietitian.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class NutrientEntry {

    // The name of the nutrient, e.g. "Fiber", "Omega-3", "Vitamin C"
    private String name;

    // The numeric value of the nutrient
    private Double value;

    // The unit of measurement, e.g. "g", "mg", "µg", "IU", "kcal"
    private String unit;

    // Percent of daily value as reported by Cronometer (null if N/T or n/a)
    private Double pct;

    public NutrientEntry() {}

    public NutrientEntry(String name, Double value, String unit, Double pct) {
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.pct = pct;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getPct() { return pct; }
    public void setPct(Double pct) { this.pct = pct; }

    @Override
    public String toString() {
        return name + ": " + value + " " + unit + (pct != null ? " (" + pct + "%)" : "");
    }
}
