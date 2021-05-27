package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerRegistrationAct extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        linearLayout = findViewById(R.id.linearLayout);
        // Update Customer Registration Activity
        addMeasurementsViews();
    }

    private void addMeasurementsViews() {
        DBHelper dbHelper = new DBHelper(this);

        // First Pair Item => MeasurementName, Second Pair Item => MeasurementType
        List<Pair<String, String>> measurementsName = dbHelper.getMeasurementsTableColumns();

        for(int i = 0 ; i < measurementsName.size(); ++i) {
            // Getting Current Measurement Name
            String measurementName = measurementsName.get(i).first;

            if(measurementsName.get(i).second.equals("Text")) {
                // Add RadioGroupView
                String radioButtonNames = dbHelper.getRadioButtonNames(measurementName);
                // Splitting RadioButton Names
                String[] names  = radioButtonNames.split("_");

                View view = getLayoutInflater().inflate(R.layout.radio_group, null);
                // Updating TextView 'Text'
                TextView textView = view.findViewById(R.id.radioTextView);
                textView.setText(measurementName);
                // Adding Radio Buttons
                RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

                for(int j = 0; j < names.length; ++j) {
                    RadioButton radioButton = new RadioButton(this);
                    // Updating Text of RadioButton
                    radioButton.setText(names[j]);
                    // Adding RadioButton in RadioGroup
                    radioGroup.addView(radioButton);
                }

                linearLayout.addView(view);
            } else if(measurementsName.get(i).second.equals("Integer")) {
                // TODO
                // New Layout
                // integeral_measurement
            }
            else {
                // For Real Measurement

                View view = getLayoutInflater().inflate(R.layout.measurement_layout, null);
                // Updating TextView 'Text'
                TextView textView = view.findViewById(R.id.measurementNameView);
                textView.setText(measurementName);
                // Adding in linearLayout
                linearLayout.addView(view);
            }
        }
    }

    public void RegisterCustomer(View view) {
        // ToDO
//        int count = linearLayout.getChildCount();
//
//        for(int i= 0; i < count; ++i) {
//            View view1 = linearLayout.getChildAt(i);
//            TextView textView = view1.findViewById(R.id.measurementNameView);
//            EditText editText = view1.findViewById(R.id.measurementValueView);
//        }

        Toast.makeText(this, "Customer Not Registered", Toast.LENGTH_LONG).show();
    }
}