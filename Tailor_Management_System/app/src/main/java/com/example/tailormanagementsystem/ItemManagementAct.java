package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ItemManagementAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_management);

        List<Item> items = null;
        try {
            items = QueryHandler.getAll(Item.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "MainActivity.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        // for loop
//            // Adding Item Details
//            View view1 = getLayoutInflater().inflate(R.layout.layout_item_details, null);
//            // Setting Items Count
//            TextView textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemNo);
//            textView.setText(Integer.toString(itemsSelected.size()));
//            // Setting Item Name
//            textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemName);
//            textView.setText(item.Name);
//            // Setting Item Price
//            textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemCost);
//            textView.setText(item.Price.toString());
//
//            // Adding to LinearLayout
//            linearLayoutItemDetails.addView(view1);
    }
}