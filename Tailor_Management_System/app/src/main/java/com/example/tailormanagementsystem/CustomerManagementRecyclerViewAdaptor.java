package com.example.tailormanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class CustomerManagementRecyclerViewAdaptor extends RecyclerView.Adapter<CustomerManagementRecyclerViewAdaptor.MyViewHolder> {

    List<Customer> customers;
    Activity mAct;

    public CustomerManagementRecyclerViewAdaptor(List<Customer> customers, Activity mAct) {
        this.customers = customers;
        this.mAct = mAct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_customer_management_recycler_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Customer customer = customers.get(position);

        holder.textViewId.setText(customer.Id.toString());
        holder.textViewCustomerName.setText(customer.Name);
        holder.textViewCustomerPhoneNumber.setText(customer.PhoneNumber);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewCustomerName;
        TextView textViewCustomerPhoneNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.customerManagementRecyclerViewLayoutTextViewCustomerNo);
            textViewCustomerName = itemView.findViewById(R.id.customerManagementRecyclerViewLayoutTextViewName);
            textViewCustomerPhoneNumber= itemView.findViewById(R.id.customerManagementRecyclerViewLayoutTextViewPhoneNumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Getting CustomerId TextView
                    TextView textView1 = v.findViewById(R.id.customerManagementRecyclerViewLayoutTextViewCustomerNo);

                    // Getting Customer Id
                    Integer customerId = Integer.parseInt(textView1.getText().toString());

                    // Displaying Id
                    Log.d("ItemClicked", Integer.toString(customerId));

                    // New Activity
                    Intent intent = new Intent(mAct, CustomerDetailsAct.class);
                    intent.putExtra("CustomerId", customerId);

                    mAct.startActivityForResult(intent, 1);
                }
            });
        }
    }


}

