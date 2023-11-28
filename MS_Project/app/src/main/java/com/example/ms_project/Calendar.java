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

    private Button showDetails;
    private FoodAdapter foodAdapter;
    private FoodRepository foodRepository;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        foodRepository = new FoodRepository(this);

        selectedDateButton = findViewById(R.id.buttonDatePicker);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        foodAdapter = new FoodAdapter(this, new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);

//        if (selectedDateButton.getText().toString().equals("날짜 선택")){
//            foodSimples.add(new FoodSimple("날짜를 선택해주세요", "", 0));
//            foodAdapter = new FoodAdapter(this, foodSimples);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(foodAdapter);
//        } else {
//            List<FoodSimple> realFoodSimples = foodRepository.getFoodSimple(selectedDateButton.getText().toString());
//            foodAdapter = new FoodAdapter(this, realFoodSimples);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(foodAdapter);
//        }


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

                        List<FoodSimple> realFoodSimples = foodRepository.getFoodSimple(selectedDate);
                        updateRecyclerView(realFoodSimples);
                    }
                },
                year, month, day
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        datePickerDialog.show();
    }
    private void updateRecyclerView(List<FoodSimple> foodSimples) {
        foodAdapter.setData(foodSimples);
        foodAdapter.notifyDataSetChanged();
    }
}
