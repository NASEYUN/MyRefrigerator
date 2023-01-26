package com.naseyun.computer.myrefrigerator;

public class Mybasket {
    String ingredient;
    String expiration_date;
    int deadline_date;

    public Mybasket(String ingredient, String expiration_date, int deadline_date) {
        this.ingredient = ingredient;
        this.expiration_date = expiration_date;
        this.deadline_date = deadline_date;
    }

    public String getIngredient() {
        return ingredient;
    }
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
    public String getExpiration_date() {
        return expiration_date;
    }
    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }
    public int getDeadline_date() {
        return deadline_date;
    }
    public void setDeadline_date(int deadline_date) {
        this.deadline_date = deadline_date;
    }
}
