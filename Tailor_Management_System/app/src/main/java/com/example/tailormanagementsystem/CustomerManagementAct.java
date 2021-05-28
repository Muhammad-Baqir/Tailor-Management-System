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

    List<Customer> allCustomers;
    List<Customer> adaptorCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);


        DBHelper dbHelper = new DBHelper(this);
        // Getting Customers
        try {
            allCustomers = QueryHandler.getAll(Customer.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "CustomerManagementAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        recyclerView = findViewById(R.id.customerManagementRecyclerView);

        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Default Display All Customers
        adaptorCustomers = new ArrayList<>();
        for(int i= 0; i < allCustomers.size(); ++i) {
            adaptorCustomers.add(allCustomers.get(i));
        }
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }

        // Displaying
        adapter = new CustomerManagementRecyclerViewAdaptor(adaptorCustomers, CustomerManagementAct.this);
        recyclerView.setAdapter(adapter);
    }

}