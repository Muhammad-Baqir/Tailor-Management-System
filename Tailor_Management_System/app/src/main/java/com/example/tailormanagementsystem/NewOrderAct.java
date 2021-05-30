package com.example.tailormanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NewOrderAct extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter = null;
    RecyclerView.LayoutManager layoutManager;

    List<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        // Getting Customers
        try {
            customers = QueryHandler.getAll(Customer.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "NewOrderAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        recyclerView = findViewById(R.id.newOrderRecyclerView);

        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Displaying
        adapter = new NewOrderRecyclerViewAdaptor(customers,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Come Back from NewOrderDetailsAct
        // Finish The Activity
        finish();
    }

}