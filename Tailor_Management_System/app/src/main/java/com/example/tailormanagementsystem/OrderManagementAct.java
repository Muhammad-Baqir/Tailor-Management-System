package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class OrderManagementAct extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);


        DBHelper dbHelper = new DBHelper(this);
        // Getting Orders
        try {
            orderList = QueryHandler.getAll(Order.class, dbHelper.getReadableDatabase());
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }

        recyclerView = findViewById(R.id.orderManagmentRecyclerView);
        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderManagementRecyclerViewAdaptor(orderList, OrderManagementAct.this);
        recyclerView.setAdapter(adapter);
    }
}