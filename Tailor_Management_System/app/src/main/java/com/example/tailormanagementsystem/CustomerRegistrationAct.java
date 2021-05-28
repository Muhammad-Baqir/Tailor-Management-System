package com.example.tailormanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRegistrationAct extends AppCompatActivity {
    LinearLayout linearLayout;
    List<Pair<String, String>> measurements;

    EditText editTextName;
    EditText editTextPhoneNo;
    EditText editTextAddress;
    EditText editTextEmail;
    RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        linearLayout = findViewById(R.id.linearLayout);
        // Update Customer Registration Activity
        addMeasurementsViews();

        // Getting Views
        editTextName = findViewById(R.id.customerRegistrationEditTextName);
        editTextPhoneNo = findViewById(R.id.customerRegistrationEditTextPhoneNo);
        editTextAddress = findViewById(R.id.customerRegistrationEditTextAddress);
        editTextEmail = findViewById(R.id.customerRegistrationEditTextEmail);
        radioGroupGender = findViewById(R.id.customerRegistrationRadioButtonGender);
    }

    private void addMeasurementsViews() {
        DBHelper dbHelper = new DBHelper(this);

        // First Pair Item => MeasurementName, Second Pair Item => MeasurementType
        measurements = dbHelper.getMeasurementsTableColumns();

        for(int i = 2 ; i < measurements.size(); ++i) {
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
        // CustomerRegistration
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String phoneNo = editTextPhoneNo.getText().toString();
        String address = editTextAddress.getText().toString();
        String gender = ((RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        // New Added Customer
        Integer customerId = -1;

        try {
            customerId = QueryHandler.add(new Customer(0, name, phoneNo, gender, address, email, 0));
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "CustomerRegistrationAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }


        List<String> measurementsValue = new ArrayList<>();

        // CustomerMeasurementsRegistration
        int count = linearLayout.getChildCount();
        for(int i = 2; i < measurements.size(); ++i) {
            // Getting Current Measurement Name&Type
            String measurementName = measurements.get(i).first;
            String measurementType = measurements.get(i).second;

            ConstraintLayout constraintLayout = (ConstraintLayout) linearLayout.getChildAt(i - 2);

            if(measurementType.equals("Text")) {
                RadioGroup radioGroup = (RadioGroup) constraintLayout.getChildAt(1);
                int id = radioGroup.getCheckedRadioButtonId();
                String textValue = "NaN";

                // Getting Value
                for(int j =0; j < radioGroup.getChildCount(); ++j) {
                    RadioButton radioButton = (RadioButton) radioGroup.getChildAt(j);

                    if(id == radioButton.getId()) {
                        textValue = radioButton.getText().toString();
                    }
                }
                Log.d("Value", textValue + ":" + measurements.get(i).first);
                // Add Measurement
                measurementsValue.add(textValue);
            } else if(measurementType.equals("Integer")) {
                // Integral Measurement
                Spinner spinner = (Spinner)constraintLayout.getChildAt(1);
                Integer integerValue = Integer.parseInt(spinner.getSelectedItem().toString());
                // Displaying
                Log.d("Value", integerValue + ":" + measurements.get(i).first);
                // Add Measurement
                measurementsValue.add(integerValue.toString());
            } else {
                // Real Measurement
                EditText editText = (EditText) constraintLayout.getChildAt(1);
                Spinner spinner = (Spinner)constraintLayout.getChildAt(2);

                // Getting Float Part
                String value =  spinner.getSelectedItem().toString();
                Float floatValue = 0f;
                switch (value) {
                    case "1/2":
                        floatValue = 0.5f;
                        break;
                    case "1/4":
                        floatValue = 0.25f;
                        break;
                    case "3/4":
                        floatValue = 0.75f;
                }

                // Getting Integral Part
                Integer integralValue = Integer.parseInt(editText.getText().toString());
                floatValue += integralValue;
                Log.d("Value", floatValue + ":" + measurements.get(i).first);
                // Add Measurement
                measurementsValue.add(floatValue.toString());
            }
        }

        // Add into Database
        DBHelper.addNewMeasurement(customerId, measurementsValue);

        Toast.makeText(this, "Customer Registered Successfully", Toast.LENGTH_LONG).show();
    }
}