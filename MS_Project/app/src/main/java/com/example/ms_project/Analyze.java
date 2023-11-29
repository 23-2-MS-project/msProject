package com.example.ms_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.ms_project.dto.AnalyzeDTO;

public class Analyze extends AppCompatActivity {
    private Button selectedDateButton;
    private FoodRepository foodRepository;
    private Context context;
    private TextView monthKcal;
    private TextView monthAvgKcal;
    private TextView breakfastCost;
    private TextView lunchCost;
    private TextView dinnerCost;
    private TextView totalMonthCost;
    private TextView avgMonthCost;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        selectedDateButton = findViewById(R.id.buttonDatePicker);
    }
    public void showDatePickerDialog(View view) {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int year = calendar.get(java.util.Calendar.YEAR);
        int month = calendar.get(java.util.Calendar.MONTH);
        int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        foodRepository = new FoodRepository(this);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // 여기서 선택된 날짜를 처리하면 됩니다.
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1);
                        selectedDateButton.setText(selectedDate);
                        AnalyzeDTO analyzeData = foodRepository.getAnalyzeData(selectedDate);
                        updateTextView(analyzeData);

                    }
                },
                year, month, day
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }
    private void updateTextView(AnalyzeDTO analyzeDTO){
        monthKcal = findViewById(R.id.MonthKcal);
        monthAvgKcal = findViewById(R.id.monthAvgKcal);
        breakfastCost = findViewById(R.id.BreakfastCost);
        lunchCost = findViewById(R.id.LunchCost);
        dinnerCost = findViewById(R.id.DinnerCost);
        totalMonthCost = findViewById(R.id.TotalMonthCost);
        avgMonthCost = findViewById(R.id.avgDailyCost);

        monthKcal.setText(String.valueOf(analyzeDTO.getTotalMonthKcal()) + " Kcal");
        monthAvgKcal.setText(String.valueOf(analyzeDTO.getAvgMonthKcal()) + " Kcal");
        breakfastCost.setText(String.valueOf(analyzeDTO.getTotalBreakfastCost()) + " 원");
        lunchCost.setText(String.valueOf(analyzeDTO.getTotalLunchCost()) + " 원");
        dinnerCost.setText(String.valueOf(analyzeDTO.getTotalDinnerCost()) + " 원");
        totalMonthCost.setText(String.valueOf(analyzeDTO.getTotalMonthCost()) + " 원");
        avgMonthCost.setText(String.valueOf(analyzeDTO.getAvgMonthCost()) + " 원");

        monthKcal.setVisibility(View.VISIBLE);
        monthAvgKcal.setVisibility(View.VISIBLE);
        breakfastCost.setVisibility(View.VISIBLE);
        lunchCost.setVisibility(View.VISIBLE);
        dinnerCost.setVisibility(View.VISIBLE);
        totalMonthCost.setVisibility(View.VISIBLE);
        avgMonthCost.setVisibility(View.VISIBLE);
    }
}