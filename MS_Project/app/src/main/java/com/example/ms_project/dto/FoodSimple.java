package com.example.ms_project.dto;

public class FoodSimple {
    private String time;
    private String type;
    private int calorie;

    // 생성자, getter 및 setter 메서드 등을 추가할 수 있습니다.

    public FoodSimple() {
        // 기본 생성자
    }

    public FoodSimple(String time, String type, int calorie) {
        this.time = time;
        this.type = type;
        this.calorie = calorie;
    }

    // Getter 및 Setter 메서드 추가
    // 예시: getTime(), setTime(), getType(), setType(), getCalorie(), setCalorie(), ...

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}

