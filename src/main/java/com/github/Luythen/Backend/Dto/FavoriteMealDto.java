package com.github.Luythen.Backend.Dto;

public class FavoriteMealDto {

    private String favoriteMealID;
    private String userID;
    private String mealTitle;
    private String mealImgSrc;
    private String mealCategory;
    private String comment;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMealTitle() {
        return mealTitle;
    }

    public void setMealTitle(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getFavoriteMealID() {
        return favoriteMealID;
    }

    public void setFavoriteMealID(String favoriteMealID) {
        this.favoriteMealID = favoriteMealID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMealImgSrc() {
        return mealImgSrc;
    }

    public void setMealImgSrc(String mealSrcImg) {
        this.mealImgSrc = mealSrcImg;
    }

}
