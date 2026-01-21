package com.github.Luythen.Backend;

import java.time.LocalDate;

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
    private String favoriteMealID;
    
    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String mealID;

    @Column(nullable = false)
    private String mealCategory;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private LocalDate date;

    public String getFavoriteMealID() {
        return favoriteMealID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
