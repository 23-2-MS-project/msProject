package com.example.ms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ms_project.dto.FoodDetail;

public class ShowFoodDetail extends AppCompatActivity {
    private Button buttonDate;
    private ImageView foodImage;
    private TextView infoTime;
    private TextView infoMain;
    private TextView infoCost;
    private TextView infoCalorie;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_detail);

        Intent intent = getIntent();

        buttonDate = findViewById(R.id.buttonDate);
        foodImage = findViewById(R.id.ShowImage);
        infoTime = findViewById(R.id.InfoTime);
        infoMain = findViewById(R.id.InfoMain);
        infoCost = findViewById(R.id.InfoCost);
        infoCalorie = findViewById(R.id.InfoCalorie);

        FoodDetail foodDetail = (FoodDetail) intent.getExtras().get("foodDetail");

        buttonDate.setText(foodDetail.getDate().toString());
        foodImage.setImageBitmap(BitmapFactory.decodeByteArray(foodDetail.getImage(), 0, foodDetail.getImage().length));
        infoTime.setText(foodDetail.getTime());
        infoMain.setText(foodDetail.getMainMenu());
        infoCost.setText(String.valueOf(foodDetail.getCost()) + " 원");
        infoCalorie.setText(String.valueOf(foodDetail.getCalorie()) + " Kcal");
    }
}