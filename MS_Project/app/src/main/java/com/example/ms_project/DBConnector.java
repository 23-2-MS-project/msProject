package com.example.ms_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBConnector extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projects";
    private static final int DATABASE_VERSION = 1;
    private static DBConnector dbHelper = null;

    public DBConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DBConnector getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBConnector(context);
        }
        return dbHelper;
    }

    // foodlist 테이블 생성 쿼리
    private static final String CREATE_FOOD_TABLE = "CREATE TABLE food (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "date TEXT," +
            "type TEXT," +
            "place TEXT," +
            "image BLOB," +
            "main_menu TEXT," +
            "side_menu TEXT," +
            "review TEXT," +
            "time TEXT," +
            "calorie TEXT," +
            "cost INTEGER);";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FOOD_TABLE);
        Log.d("DB", "생성완료");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS foodlist");
        db.execSQL("DROP TABLE IF EXISTS food");
        onCreate(db);
    }
    public long insert(ContentValues addValue, String table) {
        return getWritableDatabase().insert(table, null, addValue);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs,
                        String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int delete(String whereClause, String[] whereArgs, String table) {
        return getWritableDatabase().delete(table, whereClause,
                whereArgs);
    }
}
