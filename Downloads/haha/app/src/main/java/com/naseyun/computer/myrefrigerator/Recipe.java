package com.naseyun.computer.myrefrigerator;

import android.media.Image;
import android.net.Uri;

public class Recipe {
    Uri food_image;
    String food_title;
    String food_serving;
    String food_time;

    public Recipe(Uri food_image, String food_title, String food_serving, String food_time) {
        this.food_image = food_image;
        this.food_title = food_title;
        this.food_serving = food_serving;
        this.food_time = food_time;
    }

    public Uri getFood_image() {
        return food_image;
    }

    public String getFood_title() {
        return food_title;
    }

    public String getFood_serving() {
        return food_serving;
    }

    public String getFood_time() {
        return food_time;
    }

//    public void setIngredient(String ingredient) {
//        this.ingredient = ingredient;
//    }
//    public String getExpiration_date() {
//        return expiration_date;
//    }
//    public void setExpiration_date(String expiration_date) {
//        this.expiration_date = expiration_date;
//    }
//    public int getDeadline_date() {
//        return deadline_date;
//    }
//    public void setDeadline_date(int deadline_date) {
//        this.deadline_date = deadline_date;
//    }
}
