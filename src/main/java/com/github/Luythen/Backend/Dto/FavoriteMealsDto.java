package com.github.Luythen.Backend.Dto;

import java.util.ArrayList;

import com.github.Luythen.Backend.FavoriteMealModel;

public class FavoriteMealsDto {
    private ArrayList<FavoriteMealModel> favoriteMeals;

    public ArrayList<FavoriteMealModel> getFavoriteMeals() {
        return favoriteMeals;
    }

    public void setFavoriteMeals(ArrayList<FavoriteMealModel> favoriteMeals) {
        this.favoriteMeals = favoriteMeals;
    }
}
