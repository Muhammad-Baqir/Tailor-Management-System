package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MeasurementsDB measurementsDB = new MeasurementsDB(this);

//        measurementsDB.addMeasurement(new TailorMeasurements(0, "Shoulder", "Numeric", ""));
//        measurementsDB.addMeasurement(new TailorMeasurements(0, "Ghera", "CheckBoxes", "Goal;Choros;"));


        DBHelper dbHelper = new DBHelper(this);
    }

    // CustomerRegistrationButtonClick
    public void CustomerRegistration(View view) {
        Intent intent = new Intent(this, CustomerRegistration.class);
        startActivity(intent);
    }
}
