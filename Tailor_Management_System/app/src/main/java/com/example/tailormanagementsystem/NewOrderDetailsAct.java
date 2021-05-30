package com.example.tailormanagementsystem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewOrderDetailsAct extends AppCompatActivity {

    Integer customerId;
    LinearLayout linearLayoutItemDetails;
    int advancePayment = 0;
    final String[] statusList = new String[]{"Pending", "Completed", "Delivered"};

    // Containers
    List<Item> itemArrayList = null;
    List<Item> itemsSelected = new ArrayList<>();
    // Views
    EditText deliveryDateView;
    Spinner spinnerItems;
    Spinner spinnerStatus;
    EditText currentDateView;
    EditText quantityView;
    EditText advancePaymentView;
    EditText totalBillView;
    EditText remainingPaymentView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order_details);

        // Getting Customer Id
        customerId = getIntent().getIntExtra("CustomerId", 0);

        // Getting Views
        linearLayoutItemDetails = findViewById(R.id.newOrderDetailsLinearLayoutItemsDetail);
        deliveryDateView = findViewById(R.id.newOrderDetailsEditTextDeadline);
        spinnerItems = findViewById(R.id.newOrderDetailsSpinnerItems);
        spinnerStatus = findViewById(R.id.newOrderDetailsSpinnerStatus);
        currentDateView = findViewById(R.id.newOrderDetailsEditTextOrderDate);
        quantityView = findViewById(R.id.newOrderDetailsEditTextQuantity);
        advancePaymentView = findViewById(R.id.newOrderDetailsEditTextAdvancePayment);
        totalBillView = findViewById(R.id.newOrderDetailsEditTextTotalBill);
        remainingPaymentView = findViewById(R.id.newOrderDetailsEditTextRemainingAmount);

        // Getting Data From Database
        List<String> stringArrayList = new ArrayList<>();
        try {
            itemArrayList = QueryHandler.getAll(Item.class);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "NewOrderDetailsAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        // Setting Order Date
        currentDateView.setText(LocalDateTime.now().toLocalDate().toString());

        // Setting Item Details Spinner
        for(int i = 0; i < itemArrayList.size(); ++i) {
            stringArrayList.add(itemArrayList.get(i).Name + " : " + itemArrayList.get(i).Price);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItems.setAdapter(adapter);

        // Setting Status Spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.status, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter1);

        // Advance Payment TextChanged Listener
        advancePaymentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    advancePayment = Integer.parseInt(s.toString());
                    // Updating Remaining Amount
                }catch (Exception exception) {
                    Log.d("ParsingError", exception.getMessage());
                }

                remainingPaymentView.setText(Integer.toString(Integer.parseInt(totalBillView.getText().toString()) - advancePayment));
            }
        });

    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(deliveryDateView);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void AddNewItem(View view) {
        int position = spinnerItems.getSelectedItemPosition();

        // Finding Selected Item
        Item item = itemArrayList.get(position);
        itemsSelected.add(item);

        // Adding Item Details
        View view1 = getLayoutInflater().inflate(R.layout.layout_item_details, null);
        // Setting Items Count
        TextView textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemNo);
        textView.setText(Integer.toString(itemsSelected.size()));
        // Setting Item Name
        textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemName);
        textView.setText(item.Name);
        // Setting Item Price
        textView = view1.findViewById(R.id.newOrderDetailsItemDetailsTextViewItemCost);
        textView.setText(item.Price.toString());

        // Adding to LinearLayout
        linearLayoutItemDetails.addView(view1);

        // Calculating Quantity, Total Bill, Remaining Amount
        // Quantity
        quantityView.setText(Integer.toString(itemsSelected.size()));
        int totalBill = 0;
        for(int i = 0; i < itemsSelected.size(); ++i) {
            totalBill += itemsSelected.get(i).Price;
        }
        // Total Bill
        totalBillView.setText(Integer.toString(totalBill));
        // Remaining Amount
        remainingPaymentView.setText(Integer.toString(Integer.parseInt(totalBillView.getText().toString()) - advancePayment));
    }

    // Create New Order
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void CreateOrder(View view) {
        try {
            // Adding Order in DB
            int orderId = QueryHandler.add(new Order(0, customerId, Integer.parseInt(totalBillView.getText().toString()), Integer.parseInt(remainingPaymentView.getText().toString()), currentDateView.getText().toString(), deliveryDateView.getText().toString(), statusList[spinnerStatus.getSelectedItemPosition()]));

            // Adding OrderItems in DB
            for(int i = 0; i < itemArrayList.size(); ++i) {
                // Calculating Quantity
                Item item = itemArrayList.get(i);
                int quantity = 0;
                for(int j =0; j < itemsSelected.size(); ++j) {
                    if(itemsSelected.get(j).Id == item.Id) {
                        quantity++;
                    }
                }
                // Adding to DB
                if(quantity > 0) {
                    QueryHandler.add(new OrderItem(0, orderId, item.Id, quantity));
                }
            }

            // Checking for AdvancePayment
            if(advancePayment > 0) {
                QueryHandler.add(new Receipt(0, orderId, LocalDateTime.now().toString(), advancePayment));
            }

            Toast.makeText(this, "New Order Added Successfully", Toast.LENGTH_SHORT);
        } catch (Exception exception) {
            Toast.makeText(this, "Order Not Added", Toast.LENGTH_SHORT);
            Log.d("ExceptionLocation", "NewOrderDetailsAct.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        // Finish The Activity
        finish();
    }
}