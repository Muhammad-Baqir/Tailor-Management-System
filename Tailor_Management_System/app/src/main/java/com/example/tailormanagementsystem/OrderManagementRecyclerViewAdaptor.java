package com.example.tailormanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

class OrderManagementRecyclerViewAdaptor extends RecyclerView.Adapter<OrderManagementRecyclerViewAdaptor.MyViewHolder> {
    List<Order> orders;
    Activity mAct;
    Context context;

    public OrderManagementRecyclerViewAdaptor(List<Order> orders, Activity mAct, Context context) {
        this.orders = orders;
        this.mAct = mAct;
        this.context = context;
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

        // Getting Customer Info
        Customer customer = null;
        try {
            customer = QueryHandler.get(Customer.class, order.CustomerId);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "OrderManagementRecyclerViewAdaptor.java");
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Getting OrderId Text View & CustomerId TextView
                    TextView textView1 = v.findViewById(R.id.orderManagementRecyclerViewLayoutTextViewId);
                    TextView textView2 = v.findViewById(R.id.orderManagementRecyclerViewLayoutTextViewCustomerNo);

                    // Getting Order Id
                    Integer orderId = Integer.parseInt(textView1.getText().toString());
                    Integer customerId = Integer.parseInt(textView2.getText().toString());

                    // Displaying Id
                    Log.d("ItemClicked", Integer.toString(orderId));

                    // New Activity
                    Intent intent = new Intent(context, OrderDetailsAct.class);
                    intent.putExtra("OrderId", orderId);
                    intent.putExtra("CustomerId", customerId);
                    context.startActivity(intent);

                    // When Come back from activity, update 'orders'
                    // ToDO
                }
            });

        }
    }
}

