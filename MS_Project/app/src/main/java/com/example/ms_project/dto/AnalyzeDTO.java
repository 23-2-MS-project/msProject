package com.example.ms_project.dto;

public class AnalyzeDTO {
    private int totalMonthKcal;
    private int avgMonthKcal;
    private int totalBreakfastCost;
    private int totalLunchCost;
    private int totalDinnerCost;
    private int totalMonthCost;
    private int avgMonthCost;

    public AnalyzeDTO(int totalMonthKcal, int avgMonthKcal, int totalBreakfastCost, int totalLunchCost, int totalDinnerCost, int totalMonthCost, int avgMonthCost) {
        this.totalMonthKcal = totalMonthKcal;
        this.avgMonthKcal = avgMonthKcal;
        this.totalBreakfastCost = totalBreakfastCost;
        this.totalLunchCost = totalLunchCost;
        this.totalDinnerCost = totalDinnerCost;
        this.totalMonthCost = totalMonthCost;
        this.avgMonthCost = avgMonthCost;
    }

    public int getTotalMonthKcal() {
        return totalMonthKcal;
    }

    public void setTotalMonthKcal(int totalMonthKcal) {
        this.totalMonthKcal = totalMonthKcal;
    }

    public int getAvgMonthKcal() {
        return avgMonthKcal;
    }

    public void setAvgMonthKcal(int avgMonthKcal) {
        this.avgMonthKcal = avgMonthKcal;
    }

    public int getTotalBreakfastCost() {
        return totalBreakfastCost;
    }

    public void setTotalBreakfastCost(int totalBreakfastCost) {
        this.totalBreakfastCost = totalBreakfastCost;
    }

    public int getTotalLunchCost() {
        return totalLunchCost;
    }

    public void setTotalLunchCost(int totalLunchCost) {
        this.totalLunchCost = totalLunchCost;
    }

    public int getTotalDinnerCost() {
        return totalDinnerCost;
    }

    public void setTotalDinnerCost(int totalDinnerCost) {
        this.totalDinnerCost = totalDinnerCost;
    }

    public int getTotalMonthCost() {
        return totalMonthCost;
    }

    public void setTotalMonthCost(int totalMonthCost) {
        this.totalMonthCost = totalMonthCost;
    }

    public int getAvgMonthCost() {
        return avgMonthCost;
    }

    public void setAvgMonthCost(int avgMonthCost) {
        this.avgMonthCost = avgMonthCost;
    }
}
