package com.naseyun.computer.myrefrigerator;

public class Mybasket {
    private int ingredient_image;
    private String ingredient_title;
    private int ingredient_count;
    private String ingredient_unit;
    private String ingredient_category;
    private String expiration_date;
    private String deadline_date;
    private boolean isSelected;

    public Mybasket(int ingredient_image, String ingredient_title, int ingredient_count, String ingredient_unit, String ingredient_category, String expiration_date, String deadline_date) {
        this.ingredient_image = ingredient_image;
        this.ingredient_title = ingredient_title;
        this.ingredient_count = ingredient_count;
        this.ingredient_unit = ingredient_unit;
        this.ingredient_category = ingredient_category;
        this.expiration_date = expiration_date;
        this.deadline_date = deadline_date;
    }

    public int getIngredient_image(int ingredient_image) {
        return ingredient_image;
    }
    public void setIngredient_image(int ingredient_image) {
        this.ingredient_image = ingredient_image;
    }

    public String getIngredient() {
        return ingredient_title;
    }
    public void setIngredient(String ingredient_title) {
        this.ingredient_title = ingredient_title;
    }

    public int getIngredient_count() {
        return ingredient_count;
    }
    public void setIngredient_count(int ingredient_count) {
        this.ingredient_count = ingredient_count;
    }

    public String getIngredient_unit() {
        return ingredient_unit;
    }
    public void setIngredient_unit(String ingredient_unit) {
        this.ingredient_unit = ingredient_unit;
    }

    public String getIngredient_category() {
        return ingredient_category;
    }
    public void setIngredient_category(String ingredient_category) {
        this.ingredient_category = ingredient_category;
    }

    public String getExpiration_date() {
        return expiration_date;
    }
    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getDeadline_date() {
        return deadline_date;
    }
    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
