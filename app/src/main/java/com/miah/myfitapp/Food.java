package com.miah.myfitapp;

public class Food {

    private int id;
    private String notes;
    private int kcal,protein,carbs,fats;

    public  Food(){

    }

    public Food(String notes, int kcal, int protein, int carbs, int fats) {
        this.id = id;
        this.kcal = kcal;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }
}
