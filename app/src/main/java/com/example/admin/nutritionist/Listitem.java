package com.example.admin.nutritionist;

public class Listitem {
    private String food_name;
    private String food_id;

    public String getID() {
        return food_id;
    }

    public String getTitle() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }
}

