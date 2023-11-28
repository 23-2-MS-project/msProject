package com.example.ms_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.ms_project.dto.FoodSimple;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity {
    private Button selectedDateButton;
    private FoodAdapter foodAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        selectedDateButton = findViewById(R.id.buttonDatePicker);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<FoodSimple> foodSimples = new ArrayList<>();
        foodSimples.add(new FoodSimple("2023-11-28", "    조식", 300));
        foodSimples.add(new FoodSimple("2023-11-28", "    중식", 500));
        foodSimples.add(new FoodSimple("2023-11-28", "    석식", 400));
        foodAdapter = new FoodAdapter(this, foodSimples);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);
    }
    public void showDatePickerDialog(View view) {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // 여기서 선택된 날짜를 처리하면 됩니다.
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        selectedDateButton.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }
}