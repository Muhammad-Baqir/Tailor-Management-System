package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        updateOrders("Pending");

        // Displaying
        adapter = new OrderManagementRecyclerViewAdaptor(adaptorOrders, OrderManagementAct.this, this);
        recyclerView.setAdapter(adapter);
    }

    private void updateOrders(String status) {
        adaptorOrders.clear();

        // Getting Orders
        try {
            allOrders = QueryHandler.getAll(Order.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "OrderManagementAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }


        for(int i= 0; i < allOrders.size(); ++i) {
            if(allOrders.get(i).Status.equals(status)) {
                adaptorOrders.add(allOrders.get(i));
            }
        }

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void DisplayPendingOrder(View view) {
        adapter.currentStatus = "Pending";
        updateOrders("Pending");
    }

    public void DisplayCompletedOrder(View view) {
        adapter.currentStatus = "Completed";
        updateOrders("Completed");
    }

    public void DisplayDeliveredOrder(View view) {
        adapter.currentStatus = "Delivered";
        updateOrders("Delivered");
    }
}