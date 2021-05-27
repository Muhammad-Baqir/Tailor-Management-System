package com.example.tailormanagementsystem;
import android.database.Cursor;

public class Order extends Table {
    static public final String[] COLUMNS_NAME = new String[] {"OrderTable", "Id", "CustomerId", "TotalAmount", "RemainingAmount", "OrderDate", "Deadline", "Status"};

    Integer CustomerID;
    Integer TotalAmount;
    Integer RemainingAmount;
    String OrderDate;
    String Deadline;
    String Status;

    public Order(Integer id, Integer customerID, Integer totalAmount, Integer remainingAmount, String orderDate, String deadline, String status) {
        Id = id;
        CustomerID = customerID;
        TotalAmount = totalAmount;
        RemainingAmount = remainingAmount;
        OrderDate = orderDate;
        Deadline = deadline;
        Status = status;
    }

    public Order(Cursor cursor) {
        Id = cursor.getInt(0);
        CustomerID = cursor.getInt(1);
        TotalAmount = cursor.getInt(2);
        RemainingAmount = cursor.getInt(3);
        OrderDate = cursor.getString(4);
        Deadline = cursor.getString(5);
        Status = cursor.getString(6);
    }

    public static String[] getColumnsName() {
        return COLUMNS_NAME;
    }

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer customerID) {
        CustomerID = customerID;
    }

    public Integer getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        TotalAmount = totalAmount;
    }

    public Integer getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        RemainingAmount = remainingAmount;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
