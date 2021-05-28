package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomerRegistrationAct extends AppCompatActivity {
    LinearLayout linearLayout;
    private Spinner spinnerNumber,spinnerInteger;

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

                View view = getLayoutInflater().inflate(R.layout.layout_customer_registration_text, null);
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
            } else if(measurementType.equals("Integer")) {
                // For Integer Measurement
                View view = getLayoutInflater().inflate(R.layout.layout_customer_registration_integer, null);
                // Updating TextView 'Text'
                TextView textView = view.findViewById(R.id.measurementIntegerView);
                textView.setText(measurementName);

                // Updating Spinner
                Spinner spinner = view.findViewById(R.id.customerRegistrationSpinnerInteger);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.spinner_values_integer, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                // Adding in linearLayout
                linearLayout.addView(view);
            }
            else {
                // For Real Measurement
                View view = getLayoutInflater().inflate(R.layout.layout_customer_registration_real, null);
                // Updating TextView 'Text'
                TextView textView = view.findViewById(R.id.measurementNameView);
                textView.setText(measurementName);
                // Updating Spinner
                Spinner spinner = view.findViewById(R.id.customerRegistrationSpinnerReal);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinner_values_real, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
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