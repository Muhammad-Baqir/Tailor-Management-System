package com.example.tailormanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class OrderManagementAct extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderManagementRecyclerViewAdaptor adapter = null;
    RecyclerView.LayoutManager layoutManager;
    String currentStatus;

    List<Order> allOrders;
    List<Order> adaptorOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);


        // Getting Orders
        try {
            allOrders = QueryHandler.getAll(Order.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "OrderManagementAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        recyclerView = findViewById(R.id.orderManagmentRecyclerView);

        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Default Display Pending Orders
        adaptorOrders = new ArrayList<>();
        currentStatus = "Pending";
        updateOrders();

        // Displaying
        adapter = new OrderManagementRecyclerViewAdaptor(adaptorOrders, OrderManagementAct.this, this);
        recyclerView.setAdapter(adapter);
    }

    private void updateOrders() {
        adaptorOrders.clear();

        // Getting Orders
        try {
            allOrders = QueryHandler.getAll(Order.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "OrderManagementAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }


        for(int i= 0; i < allOrders.size(); ++i) {
            if(allOrders.get(i).Status.equals(currentStatus)) {
                adaptorOrders.add(allOrders.get(i));
            }
        }

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void DisplayPendingOrder(View view) {
        currentStatus = "Pending";
        updateOrders();
    }

    public void DisplayCompletedOrder(View view) {
        currentStatus = "Completed";
        updateOrders();
    }

    public void DisplayDeliveredOrder(View view) {
        currentStatus = "Delivered";
        updateOrders();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateOrders();
    }
}