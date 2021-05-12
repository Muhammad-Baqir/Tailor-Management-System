package com.example.tailormanagementsystem;

import java.util.Date;

public class Receipt {
    int Id;
    int OrderID;
    Date PaymentDate;
    int Amount;
    public Receipt(int id, int orderID,Date paymentDate,int amount) {
        Id = id;
        OrderID=orderID;
        PaymentDate=paymentDate;
        Amount=amount;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        PaymentDate = paymentDate;
    }
    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
