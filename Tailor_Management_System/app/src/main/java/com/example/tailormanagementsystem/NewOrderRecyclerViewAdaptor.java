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

public class NewOrderRecyclerViewAdaptor extends RecyclerView.Adapter<NewOrderRecyclerViewAdaptor.MyViewHolder> {

    List<Customer> customers;
    NewOrderAct context;

    public NewOrderRecyclerViewAdaptor(List<Customer> customers, NewOrderAct context) {
        this.customers = customers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_new_order_recycler_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Customer customer = customers.get(position);

        holder.textViewCustomerId.setText(customer.Id.toString());
        holder.textViewCustomerName.setText(customer.Name);
        holder.textViewCustomerAddress.setText(customer.Address);
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCustomerId;
        TextView textViewCustomerName;
        TextView textViewCustomerAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCustomerId = itemView.findViewById(R.id.newOrderTextViewCustomerId);
            textViewCustomerName = itemView.findViewById(R.id.newOrderTextViewCustomerName);
            textViewCustomerAddress = itemView.findViewById(R.id.newOrderTextViewCustomerAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Getting OrderId Text View & CustomerId TextView
                    TextView textView1 = v.findViewById(R.id.newOrderTextViewCustomerId);

                    // Getting Order Id
                    Integer customerId = Integer.parseInt(textView1.getText().toString());

                    // Displaying Id
                    Log.d("ItemClicked", Integer.toString(customerId));

                    // New Activity
                    Intent intent = new Intent(context, NewOrderDetailsAct.class);
                    intent.putExtra("CustomerId", customerId);

                    context.startActivityForResult(intent, 1);
                }
            });
        }
    }
}
