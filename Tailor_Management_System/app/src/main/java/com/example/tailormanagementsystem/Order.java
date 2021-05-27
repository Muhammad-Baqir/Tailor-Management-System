package com.example.tailormanagementsystem;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

public class Order extends Table {
    static public final String TABLE_NAME = "OrderTable";
    static public final String CUSTOMER_ID = "CustomerId";
    static public final String TOTAL_AMOUNT= "TotalAmount";
    static public final String REMAINING_AMOUNT = "RemainingAmount";
    static public final String ORDER_DATE = "OrderDate";
    static public final String DEADLINE = "Deadline";
    static public final String STATUS = "Status";

    Integer CustomerID;
    Integer TotalAmount;
    Integer RemainingAmount;
    String OrderDate;
    String Deadline;
    String Status;

    public Order(Integer id ,Integer customerID, Integer totalAmount, Integer remainingAmount, String orderDate, String deadline, String status) {
        Id = id;
        CustomerID = customerID;
        TotalAmount = totalAmount;
        RemainingAmount = remainingAmount;
        OrderDate = orderDate;
        Deadline = deadline;
        Status = status;
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

    //Override Methods
    static public Table newObject(Cursor cursor) {
        return new Order(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
    }


    static public ContentValues getContentValues(Order order) {
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_ID, order.CustomerID);
        cv.put(DEADLINE, order.Deadline);
        cv.put(ORDER_DATE, order.OrderDate);
        cv.put(REMAINING_AMOUNT, order.RemainingAmount);
        cv.put(TOTAL_AMOUNT, order.TotalAmount);
        cv.put(STATUS, order.Status);

        return cv;
    }
}
