package com.example.ms_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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




}
