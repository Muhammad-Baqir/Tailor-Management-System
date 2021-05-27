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

        DBHelper db = new DBHelper(this);
//
        ArrayList<Order> items = null;
        try {
//
            String date = LocalDateTime.now().toLocalDate().toString();
            QueryHandler.add(db.getWritableDatabase(), new Order(0, 1, 100, 0, date, date, "Pending"));
//            QueryHandler.update(db.getWritableDatabase(), new Order(3, 1, 100, 10, "New Date", "New Date", "Completed"));
////            QueryHandler.delete(Item.class, db.getWritableDatabase(), 3);
////            QueryHandler.delete(Item.class, db.getWritableDatabase(), 5);
////            QueryHandler.delete(Item.class, db.getWritableDatabase(), 6);
//
            items = QueryHandler.getAll(Order.class, db.getWritableDatabase());
//
            for(int i = 0; i < items.size(); ++i) {
                Order order = items.get(i);
                Log.d("Item", order.Id.toString() +  " : " + order.OrderDate + " : " + order.Deadline + " : " + order.Status );
            }
//
        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
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
