package com.example.ms_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ms_project.dto.AnalyzeDTO;
import com.example.ms_project.dto.FoodAnalyze;
import com.example.ms_project.dto.FoodDetail;
import com.example.ms_project.dto.FoodSimple;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository {
    private DBConnector dbConnector;
    public FoodRepository(Context context){
        this.dbConnector = new DBConnector(context);
    }
    public List<FoodSimple> getFoodSimple(String date){
        SQLiteDatabase db = dbConnector.getReadableDatabase();

        String[] projection = {
                "date",
                "type",
                "calorie"
        };
        Cursor cursor = db.query(
                "food",   // 테이블 이름
                projection,   // 가져올 컬럼 배열
                "date=?",
                new String[]{date},
                null,
                null,
                null
        );

        List<FoodSimple> foodSimples = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                String dateReal = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                int calorie = cursor.getInt(cursor.getColumnIndexOrThrow("calorie"));

                FoodSimple foodSimple = new FoodSimple(dateReal, type, calorie);
                foodSimples.add(foodSimple);
            }

            cursor.close();
        }

        db.close();
        return foodSimples;

    }
    public FoodDetail getFoodDetail(String date, String type) {

        SQLiteDatabase db = dbConnector.getReadableDatabase();

        FoodDetail foodDetail = null;

        // DB 조회 쿼리 작성
        String[] columns = {"place", "image", "main_menu", "side_menu", "review", "time", "calorie", "cost"};

        // DB 조회 쿼리 작성
        String selection = "date = ? AND type = ?";
        String[] selectionArgs = {date, type};

        // 쿼리 실행
        Cursor cursor = db.query("food", columns, selection, selectionArgs, null, null, null);



        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String place = cursor.getString(cursor.getColumnIndex("place"));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String mainMenu = cursor.getString(cursor.getColumnIndex("main_menu"));
                @SuppressLint("Range") String sideMenu = cursor.getString(cursor.getColumnIndex("side_menu"));
                @SuppressLint("Range") String review = cursor.getString(cursor.getColumnIndex("review"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                @SuppressLint("Range") int calorie = cursor.getInt(cursor.getColumnIndex("calorie"));
                @SuppressLint("Range") int cost = cursor.getInt(cursor.getColumnIndex("cost"));

                // FoodDetail 객체 생성
                foodDetail = new FoodDetail(date, place, image, mainMenu, sideMenu, review, time, calorie, cost);
            }

            cursor.close();
        }

        db.close();


        return foodDetail;
    }
    public AnalyzeDTO getAnalyzeData(String selectedDate){
        SQLiteDatabase db = dbConnector.getReadableDatabase();

        String[] columns = {"date", "calorie", "cost", "type"};

        // DB 조회 쿼리 작성
        String selection = "date LIKE ?";
        String[] selectionArgs = {selectedDate + "%"};

        // 쿼리 실행
        Cursor cursor = db.query("food", columns, selection, selectionArgs, null, null, null);

        List<FoodAnalyze> datas = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
                @SuppressLint("Range") int calorie = cursor.getInt(cursor.getColumnIndex("calorie"));
                @SuppressLint("Range") int cost = cursor.getInt(cursor.getColumnIndex("cost"));

                datas.add(new FoodAnalyze(date, type, cost, calorie));
            }

            cursor.close();
        }
        int[] dayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int totalMonthKcal = 0;
        int avgMonthKcal = 0;
        int breakfastCost = 0;
        int lunchCost = 0;
        int dinnerCost = 0;
        int totalMonthCost = 0;
        int avgMonthCost = 0;
        int month = 0;
        for (FoodAnalyze data : datas) {
            totalMonthKcal += data.getCalorie();
            if (data.getType().equals("조식")){
                breakfastCost += data.getCost();
            } else if (data.getType().equals("중식")) {
                lunchCost += data.getCost();
            } else {
                dinnerCost += data.getCost();
            }
            month = Integer.parseInt(data.getDate().substring(5, 7));
        }
        totalMonthCost = breakfastCost + lunchCost + dinnerCost;
        avgMonthCost = totalMonthCost / dayOfMonth[month-1];
        avgMonthKcal = totalMonthKcal / dayOfMonth[month-1];
        AnalyzeDTO analyzeDTO = new AnalyzeDTO(totalMonthKcal, avgMonthKcal, breakfastCost, lunchCost, dinnerCost, totalMonthCost, avgMonthCost);
        db.close();

        return analyzeDTO;
    }

}
