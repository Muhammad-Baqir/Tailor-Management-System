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

import java.util.List;

class PaymentSummaryRecyclerViewAdapter extends RecyclerView.Adapter<PaymentSummaryRecyclerViewAdapter.MyViewHolder> {
    List<Receipt> receipts;
    PaymentSummaryAct context;
    public PaymentSummaryRecyclerViewAdapter(List<Receipt> receipts, PaymentSummaryAct context) {
        this.receipts = receipts;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_payment_summary_recycler_view_addapter, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PaymentSummaryRecyclerViewAdapter.MyViewHolder holder, int position) {
        Receipt receipt = receipts.get(position);

        holder.textViewId.setText(receipt.Id.toString());
        holder.textViewDate.setText(receipt.PaymentDate);
        holder.textViewAmount.setText(receipt.Amount.toString());

        // Getting Order&Customer Info
        Order order = null;
        Customer customer = null;
        try {
            order = QueryHandler.get(Order.class, receipt.OrderID);
            customer = QueryHandler.get(Customer.class, order.CustomerId);
        } catch (Exception exception) {
            Log.d("ExceptionLocation", "PaymentSummaryRecyclerViewAdaptor.java");
            Log.d("ExceptionDetail", exception.getMessage());
        }

        holder.textViewCustomerName.setText(customer.Name);
    }

    @Override
    public int getItemCount() {
        return receipts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId;
        TextView textViewCustomerName;
        TextView textViewDate;
        TextView textViewAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.paymentSummaryRecyclerViewLayoutTextViewId);
            textViewCustomerName = itemView.findViewById(R.id.paymentSummaryRecyclerViewLayoutTextViewCustomerName);
            textViewDate = itemView.findViewById(R.id.paymentSummaryRecyclerViewLayoutTextViewPDate);
            textViewAmount = itemView.findViewById(R.id.paymentSummaryRecyclerViewLayoutTextViewBill);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Getting PaymentId Text View
                    TextView textView1 = v.findViewById(R.id.paymentSummaryRecyclerViewLayoutTextViewId);

                    // Getting Payment Id
                    Integer paymentId = Integer.parseInt(textView1.getText().toString());

                    // Getting Order Id
                    Integer orderId=0;
                    Integer customerId=0;
                    try {
                        orderId = QueryHandler.get(Receipt.class, paymentId).OrderID;
                        customerId = QueryHandler.get(Order.class, orderId).CustomerId;
                    } catch (Exception exception) {
                        Log.d("ExceptionLocation", "PaymentSummaryRecyclerViewAdaptor.java");
                        Log.d("ExceptionDetail", exception.getMessage());
                    }

                    // Displaying Id
                    Log.d("ItemClicked", Integer.toString(paymentId));

                    // New Activity
                    Intent intent = new Intent(context, OrderDetailsAct.class);
                    intent.putExtra("OrderId", orderId);
                    intent.putExtra("CustomerId", customerId);

                    context.startActivityForResult(intent, 1);
                }
            });
        }
    }
}