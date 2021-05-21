package com.example.tailormanagementsystem;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    int Id;
    int CustomerID;
    int TotalAmount;
    int RemainingAmount;
    Date OrderDate;
    Date Deadline;
    String Status;

    public Order(int id,int customerID,Date orderDate,Date deadline,int totalAmount,int remainingAmount,String status) {
        Id = id;
        CustomerID=customerID;
        OrderDate=orderDate;
        Deadline=deadline;
        TotalAmount=totalAmount;
        RemainingAmount=remainingAmount;
        Status=status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }
    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        TotalAmount = totalAmount;
    }
    public int getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        RemainingAmount = remainingAmount;
    }
    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }
    public Date getDeadline() {
        return Deadline;
    }
    public void setDeadline(Date deadline) {
        Deadline = deadline;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) { Status = status; }

}
