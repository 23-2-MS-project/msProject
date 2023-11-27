package com.example.ms_project;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class InsertFood extends AppCompatActivity {
    private Button selectedDateButton;
    private Spinner mealClass;
    private Spinner mealPlace;
    private EditText mainMenuText;
    private EditText sideMenuText;
    private EditText reviewText;
    private EditText mealCost;
    private ImageView imageView;
    private Button chooseImageButton;
    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private TimePicker timePicker;
    private Button inputTime;
    private Button addButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_food);

        selectedDateButton = findViewById(R.id.btnDatePicker);
        mealClass = findViewById(R.id.spinner_meal);
        mealPlace = findViewById(R.id.spinner_place);
        mainMenuText = findViewById(R.id.fillMainMenu);
        sideMenuText = findViewById(R.id.fillSideMenu);
        reviewText = findViewById(R.id.editReview);
        mealCost = findViewById(R.id.mealBudgt);
        addButton = findViewById(R.id.buttonAdd);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDatabase();
            }
        });



        Spinner spinnerMeals = findViewById(R.id.spinner_meal);

        // Spinner에 사용할 어댑터 설정
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.meals_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMeals.setAdapter(adapter);
        spinnerMeals.setBackgroundResource(R.drawable.spinner_boarder);

        spinnerMeals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 항목 처리
                String selectedMeal = parent.getItemAtPosition(position).toString();
                showToast("선택된 식사: " + selectedMeal);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무것도 선택되지 않았을 때의 처리
            }
        });

        Spinner spinnerPlace = findViewById(R.id.spinner_place);

        // Spinner에 사용할 어댑터 설정
        ArrayAdapter<CharSequence> adapterPlace = ArrayAdapter.createFromResource(
                this,
                R.array.place_array,
                android.R.layout.simple_spinner_item
        );
        adapterPlace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlace.setAdapter(adapterPlace);
        spinnerPlace.setBackgroundResource(R.drawable.spinner_boarder);

        spinnerPlace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 항목 처리
                String selectedPlace = parent.getItemAtPosition(position).toString();
                showToast("선택된 식당: " + selectedPlace);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 아무것도 선택되지 않았을 때의 처리
            }
        });

        chooseImageButton = findViewById(R.id.buttonImage);
        imageView = findViewById(R.id.imageView);

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null && data.getData() != null) {
                    handleImageResult(data.getData());
                }
            }
        });

        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        timePicker = findViewById(R.id.timePicker);
        inputTime = findViewById(R.id.inputTime);

        inputTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

    }
    private void showTimePickerDialog() {
        // 현재 시간 가져오기
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        // TimePickerDialog 띄우기
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 사용자가 선택한 시간을 버튼에 표시
                        inputTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                },
                currentHour,
                currentMinute,
                false  // 24시간 형식으로 표시 여부
        );

        timePickerDialog.show();
    }
    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }
    private void handleImageResult(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showDatePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

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
    private void saveDataToDatabase() {
        String selectedDate = selectedDateButton.getText().toString();
        String selectedMeal = mealClass.getSelectedItem().toString();
        String selectedPlace = mealPlace.getSelectedItem().toString()/* 사용자가 선택한 식사 장소 */;
        byte[] selectedImage = getBytes(((BitmapDrawable) imageView.getDrawable()).getBitmap());/* 사용자가 선택한 이미지 */;
        String review = reviewText.getText().toString()/* 사용자가 입력한 리뷰 */;
        String selectedTime = inputTime.getText().toString();
        int price = Integer.parseInt(mealCost.getText().toString())/* 사용자가 입력한 비용 */;
        String mainMenu = mainMenuText.getText().toString()/* 사용자가 입력한 메인 메뉴 이름 */;
        String sideMenu = sideMenuText.getText().toString()/* 사용자가 입력한 사이드 메뉴 이름 */;


        // DB에 저장할 데이터를 ContentValues에 넣기
        ContentValues foodValues = new ContentValues();
        foodValues.put("date", selectedDate);
        foodValues.put("type", selectedMeal);
        foodValues.put("place", selectedPlace);
        foodValues.put("image", selectedImage);
        foodValues.put("main_menu", mainMenu);
        foodValues.put("side_menu", sideMenu);
        foodValues.put("review", review);
        foodValues.put("time", selectedTime);
        foodValues.put("cost", price);

        // DB에 데이터 삽입
        DBConnector.getInstance(this).insert(foodValues, "food");
        showToast("저장 되었습니다!");
    }

    // 이미지를 BLOB 형태로 변환하는 메서드
    private byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

}
