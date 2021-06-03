package com.example.tailormanagementsystem;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentSummaryAct extends AppCompatActivity {
    RecyclerView recyclerView;
    PaymentSummaryRecyclerViewAdapter adapter = null;
    RecyclerView.LayoutManager layoutManager;
    String currentStatus;

    List<Receipt> allReceipts;
    List<Receipt> adaptorReceipts;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);


        // Getting Orders
        try {
            allReceipts = QueryHandler.getAll(Receipt.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "PaymentSummaryAct.java, onCreate");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        recyclerView = findViewById(R.id.paymentSummaryRecyclerView);

        // Set Layout Manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Default Display Pending Orders
        adaptorReceipts = new ArrayList<>();
        currentStatus = "Daily";
        updateReceipts();

        // Displaying
        adapter = new PaymentSummaryRecyclerViewAdapter(adaptorReceipts,  this);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateReceipts() {
        adaptorReceipts.clear();

        // Getting Orders
        try {
            allReceipts = QueryHandler.getAll(Receipt.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "PaymentSummaryAct.java, updateReceipts");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        if(currentStatus.equals("Daily"))
        {
            for(int i= 0; i < allReceipts.size(); ++i) {
                if(allReceipts.get(i).PaymentDate.equals(LocalDateTime.now().toLocalDate().toString())) {
                    adaptorReceipts.add(allReceipts.get(i));
                }
            }
        }
        else {
            if(currentStatus.equals("Monthly"))
            {
                for(int i= 0; i < allReceipts.size(); ++i) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = format.parse(allReceipts.get(i).PaymentDate);
                        // Finding Current + Payment Month
                        Integer paymentMonth = Integer.parseInt((String) DateFormat.format("MM",   date));
                        date = format.parse(LocalDateTime.now().toLocalDate().toString());
                        Integer currentMonth = Integer.parseInt((String) DateFormat.format("MM",   date));

                        if(paymentMonth.equals(currentMonth))
                        {
                            adaptorReceipts.add(allReceipts.get(i));
                        }
                    }catch (Exception exception) {
                        Log.d("ExceptionLocation", "PaymentSummaryAct.java, updateReceipts");
                        Log.d("ExceptionDetail", exception.getMessage());
                    }

                }
            }
            else{
                if(currentStatus.equals("Yearly"))
                {
                    for(int i= 0; i < allReceipts.size(); ++i) {
                        try {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = format.parse(allReceipts.get(i).PaymentDate);
                            // Finding Current and Payment Year
                            Integer paymentYear=Integer.parseInt((String) DateFormat.format("yyyy",   date));
                            date=format.parse(LocalDateTime.now().toLocalDate().toString());
                            Integer currentYear=Integer.parseInt((String) DateFormat.format("yyyy",   date));

                            if(paymentYear.equals(currentYear))
                            {
                                adaptorReceipts.add(allReceipts.get(i));
                            }
                        } catch (Exception exception) {
                            Log.d("ExceptionLocation", "PaymentSummaryAct.java, updateReceipts");
                            Log.d("ExceptionDetail", exception.getMessage());
                        }

                    }
                }
            }
        }

        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void DisplayDailyPayments(View view) {
        currentStatus = "Daily";
        updateReceipts();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void DisplayMonthlyPayments(View view) {
        currentStatus = "Monthly";
        updateReceipts();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void DisplayYearlyPayments(View view) {
        currentStatus = "Yearly";
        updateReceipts();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateReceipts();
    }
}