package com.example.ms_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

}
