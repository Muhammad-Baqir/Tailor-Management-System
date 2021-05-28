package com.example.tailormanagementsystem;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

public class NewOrderRecyclerViewAdaptor extends RecyclerView.Adapter<NewOrderRecyclerViewAdaptor.MyViewHolder> {
    List<Order> orders;
    Activity mAct;


    public NewOrderRecyclerViewAdaptor(List<Order> adaptorOrders, NewOrderAct newOrderAct) {
        this.orders = adaptorOrders;
        this.mAct = newOrderAct;
    }

    @NonNull
    @Override
    public NewOrderRecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_managment_recycler_view_layout, parent, false);
        return new NewOrderRecyclerViewAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewOrderRecyclerViewAdaptor.MyViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.textViewId.setText(order.Id.toString());
        holder.textViewCustomerNo.setText(order.CustomerId.toString());

        // Getting Customer Info
        Customer customer = null;
        try {
            customer = QueryHandler.get(Customer.class, order.CustomerId);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "NewOrderRecyclerViewAdaptor.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }
        holder.textViewName.setText(customer.Name);
        holder.textViewDate.setText(order.Deadline);
    }
    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewCustomerNo;
        TextView textViewName;
        TextView textViewDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.orderManagementRecyclerViewLayoutTextViewId);
            textViewCustomerNo = itemView.findViewById(R.id.orderManagementRecyclerViewLayoutTextViewCustomerNo);
            textViewName = itemView.findViewById(R.id.orderManagementRecyclerViewLayoutTextViewName);
            textViewDate = itemView.findViewById(R.id.orderManagementRecyclerViewLayoutTextViewDDate);
        }



    }
}
