package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);

        ArrayList<Item> items = null;
        try {

//            QueryHandler.add(db.getWritableDatabase(), new Item(0, "New Item", 10));
//            QueryHandler.update(db.getWritableDatabase(), new Item(1, "Item Updated", 20));
//            QueryHandler.delete(Item.class, db.getWritableDatabase(), 1);
//            QueryHandler.delete(Item.class, db.getWritableDatabase(), 2);
//            QueryHandler.delete(Item.class, db.getWritableDatabase(), 4);

            items = QueryHandler.getAll(Item.class, db.getWritableDatabase());

            for(int i = 0; i < items.size(); ++i) {
                Log.d("Item", items.get(i).Id + ":" + items.get(i).Name);
            }

        } catch (Exception exception) {
            Log.d("Exception", exception.getMessage());
        }


    }

    // CustomerRegistrationButtonClick
    public void CustomerRegistration(View view) {
        Intent intent = new Intent(this, CustomerRegistration.class);
        startActivity(intent);
    }
}
