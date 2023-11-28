package com.example.ms_project.dto;

import java.io.Serializable;

public class FoodDetail implements Serializable {
    private int id;
    private String date;
    private String type;
    private String place;
    private byte[] image;
    private String mainMenu;
    private String sideMenu;
    private String review;
    private String time;
    private int calorie;
    private int cost;

    // 생성자, getter 및 setter 메서드 등을 추가할 수 있습니다.

    public FoodDetail() {
        // 기본 생성자
    }

    public FoodDetail(String date, String place, byte[] image, String mainMenu,
                      String sideMenu, String review, String time, int calorie, int cost) {
        this.date = date;
        this.place = place;
        this.image = image;
        this.mainMenu = mainMenu;
        this.sideMenu = sideMenu;
        this.review = review;
        this.time = time;
        this.calorie = calorie;
        this.cost = cost;
    }

    // Getter 및 Setter 메서드 추가
    // 예시: getId(), setId(), getDate(), setDate(), ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(String mainMenu) {
        this.mainMenu = mainMenu;
    }

    public String getSideMenu() {
        return sideMenu;
    }

    public void setSideMenu(String sideMenu) {
        this.sideMenu = sideMenu;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

