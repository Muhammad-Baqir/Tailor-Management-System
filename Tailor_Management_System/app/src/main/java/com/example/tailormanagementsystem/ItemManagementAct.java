package com.example.tailormanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemManagementAct extends AppCompatActivity {
    LinearLayout linearLayout;

    EditText editTextName;
    EditText editTextCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_management);
        linearLayout=findViewById(R.id.itemManagementLinearLayout);
        editTextName=findViewById(R.id.itemManagementEditName);
        editTextCost=findViewById(R.id.itemManagementEditCost);
        List<Item> items = null;
        try {
            items = QueryHandler.getAll(Item.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "MainActivity.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }
        for (int i=0;i<items.size();++i)
        {
            // Adding Item Details
            View view1 = getLayoutInflater().inflate(R.layout.layout_item_details, null);
            // Setting Items Id
            TextView textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemNo);
            textView.setText(Integer.toString(items.get(i).Id));
            // Setting Item Name
            textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemName);
            textView.setText(items.get(i).Name);
            // Setting Item Price
            textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemCost);
            textView.setText(items.get(i).Price.toString());

            // Adding to LinearLayout
            linearLayout.addView(view1);
        }
    }
    public void RegisterItem(View view) {
        // ItemRegistration
        String name = editTextName.getText().toString();
        String cost = editTextCost.getText().toString();
      // New Added Item
        Integer itemId = -1;

        try {
            Integer price=Integer.parseInt(cost);
            if(price<=0)
            {
                Toast.makeText(this, "Cost Lower than 0 not allowed", Toast.LENGTH_LONG).show();
                return;
            }
            itemId = QueryHandler.add(new Item(0,name,price));
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "CustomerRegistrationAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }
        View view1 = getLayoutInflater().inflate(R.layout.layout_item_details, null);
        // Setting Items Id
        TextView textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemNo);
        textView.setText(Integer.toString(itemId));
        // Setting Item Name
        textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemName);
        textView.setText(name);
        // Setting Item Price
        textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemCost);
        textView.setText(cost);

        // Adding to LinearLayout
        linearLayout.addView(view1);
        Toast.makeText(this, "Item added Successfully", Toast.LENGTH_LONG).show();
    }

}