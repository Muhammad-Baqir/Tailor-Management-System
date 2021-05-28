package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;

public class OrderDetailsAct extends AppCompatActivity {
    Integer orderId;
    Integer customerId;
    Spinner spinner;

    final String[] status = new String[]{"Pending", "Completed", "Delivered"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Getting OrderId & CustomerId
        orderId = getIntent().getIntExtra("OrderId", 0);
        customerId = getIntent().getIntExtra("CustomerId", 0);

        // Getting TextViews
        EditText editTextOrderId = findViewById(R.id.activityOrderDetailsEditTextOrderId);
        EditText editTextCustomerNo = findViewById(R.id.activityOrderDetailsEditTextCustomerId);
        EditText editTextDeliveryDate = findViewById(R.id.activityOrderDetailsEditTextDeliveryDate);
        EditText editTextOrderDate = findViewById(R.id.activityOrderDetailsEditTextOrderDate);
        EditText editTextName = findViewById(R.id.activityOrderDetailsEditTextName);
        EditText editTextPhoneNo = findViewById(R.id.activityOrderDetailsEditTextNamePhoneNo);
        EditText editTextQuantity = findViewById(R.id.activityOrderDetailsEditTextNameQuantity);
        EditText editTextAdvancePayment = findViewById(R.id.activityOrderDetailsEditTextAdvancePayment);
        EditText editTextTotalBill = findViewById(R.id.activityOrderDetailsEditTextTotalBill);
        EditText editTextRemainingAmount = findViewById(R.id.activityOrderDetailsEditTextRemainingAmount);
        EditText editTextAddress = findViewById(R.id.activityOrderDetailsEditTextAddress);

        // Getting Data From Database
        Order order = null;
        Customer customer = null;
        try {
            order = QueryHandler.get(Order.class, orderId);
            customer = QueryHandler.get(Customer.class, customerId);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "OrderDetailsAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        // Setting Data
        editTextOrderId.setText(orderId.toString());
        editTextCustomerNo.setText(customerId.toString());
        editTextAdvancePayment.setText(Integer.toString(order.TotalAmount - order.RemainingAmount));
        editTextDeliveryDate.setText(order.Deadline);
        editTextName.setText(customer.Name);
        editTextOrderDate.setText(order.OrderDate);
        editTextPhoneNo.setText(customer.PhoneNumber);
        editTextQuantity.setText("NaN");
        editTextTotalBill.setText(order.TotalAmount.toString());
        editTextAddress.setText(customer.Address);
        editTextRemainingAmount.setText(order.RemainingAmount.toString());

        // Setting Spinner
        spinner = findViewById(R.id.activityOrderDetailsEditTextSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Setting Selection of Spinner
        int selection = 0;

        switch (order.Status) {
            case "Completed":
                selection = 1;
                break;
            case "Delivered":
                selection = 2;
                break;
        }

        // Setting Spinner Value
        spinner.setSelection(selection);
    }

    public void UpdateOrder(View view) {
        int i = spinner.getSelectedItemPosition();
        Order.updateOrderStatus(orderId, status[i]);
        // Go Back
        finish();
    }
}