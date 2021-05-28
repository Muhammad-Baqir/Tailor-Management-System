package com.example.tailormanagementsystem;

import android.database.Cursor;

import java.util.Date;

public class Receipt extends Table {
    static public final String[] COLUMNS_NAME = new String[] {"ReceiptTable", "Id", "OrderID", "PaymentDate", "Amount"};

    Integer OrderID;
    String PaymentDate;
    Integer Amount;

    public Receipt(Integer id, Integer orderID, String paymentDate, Integer amount) {
        Id = id;
        OrderID = orderID;
        PaymentDate = paymentDate;
        Amount = amount;
    }

    public Receipt(Cursor cursor) {
        Id = cursor.getInt(0);
        OrderID = cursor.getInt(1);
        PaymentDate = cursor.getString(2);
        Amount = cursor.getInt(3);
    }

    public static String[] getColumnsName() {
        return COLUMNS_NAME;
    }

    public Integer getOrderID() {
        return OrderID;
    }

    public void setOrderID(Integer orderID) {
        OrderID = orderID;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
