package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Must Include Line
        QueryHandler.createNewDB(this);

        //
        ArrayList<Order> items = null;
        try {
//            String date = LocalDateTime.now().toLocalDate().toString();
//            QueryHandler.add(new Customer(0,"Baqir", "234342", "Male", "SDfdsf", "ssfd", 0));
//            QueryHandler.add(new Order(0, 1, 100, 0, date, date, "Delivered"));
//            QueryHandler.add(new Order(0, 1, 100, 0, date, date, "Completed"));
//            QueryHandler.add(new Order(0, 1, 100, 0, date, date, "Pending"));

            items = QueryHandler.getAll(Order.class);

            for(int i = 0; i < items.size(); ++i) {
                Order order = items.get(i);
                Log.d("Item", order.Id.toString() +  " : " + order.OrderDate + " : " + order.Deadline + " : " + order.Status );
            }
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "MainActivity.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }


    }

    // CustomerRegistrationButtonClick
    public void CustomerRegistration(View view) {
        Intent intent = new Intent(this, CustomerRegistrationAct.class);
        startActivity(intent);
    }

    // Order Management Button Clicked
    public void OrderManagement(View view) {
        Intent intent = new Intent(this, OrderManagementAct.class);
        startActivity(intent);
    }
}
