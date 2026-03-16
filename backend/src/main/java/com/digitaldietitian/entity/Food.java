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

    @Column(name = "serving_size")
    private String servingSize;   // e.g. "1 cup (255g)"

    @ElementCollection
    @CollectionTable(
        name = "food_nutrients",
        joinColumns = @JoinColumn(name = "food_id")
    )
    private List<NutrientEntry> nutrients = new ArrayList<>();

    public Food() {}

    public Food(String name, String servingSize) {
        this.name = name;
        this.servingSize = servingSize;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getServingSize() { return servingSize; }
    public void setServingSize(String servingSize) { this.servingSize = servingSize; }
    public List<NutrientEntry> getNutrients() { return nutrients; }
    public void setNutrients(List<NutrientEntry> nutrients) { this.nutrients = nutrients; }
}