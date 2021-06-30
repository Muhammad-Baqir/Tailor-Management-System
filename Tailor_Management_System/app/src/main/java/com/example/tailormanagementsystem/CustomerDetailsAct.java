package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Pair;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsAct extends AppCompatActivity {


    LinearLayout linearLayout;
    List<Pair<String, String>> measurements;
    Integer customerId;
    Customer customer = null;

    TextView TextViewCustomerId;
    EditText EditTextName;
    EditText EditTextPhoneNo;
    EditText EditTextAddress;
    EditText EditTextEmail;
    RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details_act);

        // Getting CustomerId
        customerId = getIntent().getIntExtra("CustomerId", 0);

        linearLayout = findViewById(R.id.linearLayout);
        // Update Customer Registration Activity
        addMeasurementsViews();

        // Getting Views
        TextViewCustomerId = findViewById(R.id.customerDetailsTextViewCustomerId);
        EditTextName = findViewById(R.id.customerDetailsEditTextName);
        EditTextPhoneNo = findViewById(R.id.customerDetailsEditTextPhoneNo);
        EditTextAddress = findViewById(R.id.customerDetailsEditTextAddress);
        EditTextEmail = findViewById(R.id.customerDetailsEditTextEmail);
        radioGroupGender = findViewById(R.id.customerDetailsRadioButtonGender);

        // Getting Data From Database
        List<OrderItem> orderItemList = null;
        try {
            customer = QueryHandler.get(Customer.class, customerId);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "CustomerDetailsAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        // Setting Data
        TextViewCustomerId.setText(customerId.toString());
        EditTextName.setText(customer.Name);
        EditTextPhoneNo.setText(customer.PhoneNumber);
        EditTextAddress.setText(customer.Address);
        EditTextEmail.setText(customer.Email);
        RadioButton button;
        if (customer.getGender().equals("Male"))
        {
            button = radioGroupGender.findViewById(R.id.customerDetailsRadioButtonMale);
        }else{
            button = radioGroupGender.findViewById(R.id.customerDetailsRadioButtonFemale);
        }
        button.setChecked(true);
    }

    private void addMeasurementsViews() {
        // First Pair Item => MeasurementName, Second Pair Item => MeasurementType
        measurements = DBHelper.getMeasurementsTableColumns();

        for(int i = 2 ; i < measurements.size(); ++i) {
            // Getting Current Measurement Name&Type
            String measurementName = measurements.get(i).first;
            String measurementType = measurements.get(i).second;

            if(measurementType.equals("Text")) {
                // Add RadioGroupView
                String radioButtonNames = DBHelper.getRadioButtonNames(measurementName);
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

    public void UpdateCustomer(View view) {
        customer.Name = EditTextName.getText().toString();
        customer.Email = EditTextEmail.getText().toString();
        customer.PhoneNumber = EditTextPhoneNo.getText().toString();
        customer.Address = EditTextAddress.getText().toString();
        customer.Gender = ((RadioButton)findViewById(radioGroupGender.getCheckedRadioButtonId())).getText().toString();
        customer.Id = customerId;

        // Updating Customers
        try {
            QueryHandler.update(customer);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "CustomerDetailsAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        Toast.makeText(this, "Customer Updated Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}