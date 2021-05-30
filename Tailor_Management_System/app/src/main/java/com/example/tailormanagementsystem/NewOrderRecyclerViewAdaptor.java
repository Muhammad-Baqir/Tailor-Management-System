package com.example.tailormanagementsystem;

import android.app.Activity;
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
    Activity mAct;

    public NewOrderRecyclerViewAdaptor(List<Customer> customers, Activity mAct) {
        this.customers = customers;
        this.mAct = mAct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_order_managment_recycler_view, parent, false);
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
        }



    }
}
