package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NewOrderAct extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter = null;
    RecyclerView.LayoutManager layoutManager;

    List<Order> allOrders;
    List<Order> adaptorOrders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        DBHelper dbHelper = new DBHelper(this);
        // Getting Orders
        try {
            allOrders = QueryHandler.getAll(Order.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "OrderManagementAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }
        recyclerView = findViewById(R.id.newOrderRecyclerView);

        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Default Display Pending Orders
        adaptorOrders = new ArrayList<>();
        updateOrders("Pending");

        // Displaying
        adapter = new NewOrderRecyclerViewAdaptor(adaptorOrders, NewOrderAct.this);
        recyclerView.setAdapter(adapter);

    }

    private void updateOrders(String status) {
        adaptorOrders.clear();

        for(int i= 0; i < allOrders.size(); ++i) {
            if(allOrders.get(i).Status.equals(status)) {
                adaptorOrders.add(allOrders.get(i));
            }
        }

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}