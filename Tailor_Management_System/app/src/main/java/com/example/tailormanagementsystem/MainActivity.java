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
    }

    // CustomerRegistrationButtonClick
    public void CustomerRegistration(View view) {
        Intent intent = new Intent(this, CustomerRegistration.class);
        startActivity(intent);
    }
}
