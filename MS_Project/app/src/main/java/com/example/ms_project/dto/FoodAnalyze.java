package com.example.ms_project.dto;

public class FoodAnalyze {
    private String date;
    private int cost;
    private int calorie;
    private String type;


    public FoodAnalyze(String date, String type, int cost, int calorie) {
        this.date = date;
        this.type = type;
        this.cost = cost;
        this.calorie = calorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
