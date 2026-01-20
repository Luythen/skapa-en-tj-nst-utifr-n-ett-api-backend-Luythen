package com.github.Luythen.Backend;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FavoriteMeal")
public class FavoriteMealModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID favoriteMealID;
    
    @Column(nullable = false)
    private UUID userID;

    @Column(nullable = false)
    private int MealID;

    @Column(nullable = false)
    private String mealCategory;


}
