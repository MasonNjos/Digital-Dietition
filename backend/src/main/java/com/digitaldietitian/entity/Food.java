package com.digitaldietitian.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        // e.g. "Salmon", "Spinach"

    private String category;    // e.g. "Fish", "Leafy Green"

    @ElementCollection
    @CollectionTable(
        name = "food_nutrients",
        joinColumns = @JoinColumn(name = "food_id")
    )
    private List<NutrientEntry> nutrients = new ArrayList<>();

    public Food() {}

    public Food(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public List<NutrientEntry> getNutrients() { return nutrients; }
    public void setNutrients(List<NutrientEntry> nutrients) { this.nutrients = nutrients; }
}