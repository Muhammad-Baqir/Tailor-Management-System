package com.example.tailormanagementsystem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class OrderManagementRecyclerViewAdaptor extends RecyclerView.Adapter<OrderManagementRecyclerViewAdaptor.MyViewHolder> {

    List<Order> orders;
    Activity mAct;

    public OrderManagementRecyclerViewAdaptor(List<Order> orders, Activity mAct) {
        this.orders = orders;
        this.mAct = mAct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_managment_recycler_view_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.textViewId.setText(order.Id.toString());
        holder.textViewCustomerNo.setText(order.CustomerId.toString());

        // ToDo

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

