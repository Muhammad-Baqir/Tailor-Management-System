package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagementAct extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter = null;
    RecyclerView.LayoutManager layoutManager;

    List<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);

        // Getting Customers
        try {
            customers = QueryHandler.getAll(Customer.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "CustomerManagementAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        recyclerView = findViewById(R.id.customerManagementRecyclerView);

        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Displaying
        adapter = new CustomerManagementRecyclerViewAdaptor(customers, CustomerManagementAct.this);
        recyclerView.setAdapter(adapter);
    }

}