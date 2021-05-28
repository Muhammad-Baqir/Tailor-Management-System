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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerRegistrationAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    LinearLayout linearLayout;
    private Spinner spinnerNumber,spinnerInteger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        linearLayout = findViewById(R.id.linearLayout);
        // Update Customer Registration Activity
        addMeasurementsViews();

        spinnerNumber = findViewById(R.id.spinnerNumber);
        String[] textSizes = getResources().getStringArray(R.array.numbers);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,textSizes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNumber.setAdapter(adapter1);

        spinnerInteger = findViewById(R.id.spinnerInteger);
        String[] int1 = getResources().getStringArray(R.array.integer);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,int1);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInteger.setAdapter(adapter2);
    }

    private void addMeasurementsViews() {
        DBHelper dbHelper = new DBHelper(this);

        // First Pair Item => MeasurementName, Second Pair Item => MeasurementType
        List<Pair<String, String>> measurements = dbHelper.getMeasurementsTableColumns();

        for(int i = 0 ; i < measurements.size(); ++i) {
            // Getting Current Measurement Name&Type
            String measurementName = measurements.get(i).first;
            String measurementType = measurements.get(i).second;

            if(measurementType.equals("Text")) {
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
                // For Integer Measurement

                View view = getLayoutInflater().inflate(R.layout.measurement_layout_integer, null);
                // Updating TextView 'Text'
                TextView textView = view.findViewById(R.id.measurementIntegerView);
                textView.setText(measurementName);
                // Adding in linearLayout
                linearLayout.addView(view);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}