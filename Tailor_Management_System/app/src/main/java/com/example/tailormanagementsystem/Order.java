package com.example.tailormanagementsystem;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Order extends Table {
    static public final String[] COLUMNS_NAME = new String[] {"OrderTable", "Id", "CustomerId", "TotalAmount", "RemainingAmount", "OrderDate", "Deadline", "Status"};
  
    Integer CustomerId;
    Integer TotalAmount;
    Integer RemainingAmount;
    String OrderDate;
    String Deadline;
    String Status;

    public Order(Integer id, Integer customerID, Integer totalAmount, Integer remainingAmount, String orderDate, String deadline, String status) {
        Id = id;
        CustomerId = customerID;
        TotalAmount = totalAmount;
        RemainingAmount = remainingAmount;
        OrderDate = orderDate;
        Deadline = deadline;
        Status = status;
    }

    public Order(Cursor cursor) {
        Id = cursor.getInt(0);
        CustomerId = cursor.getInt(1);
        TotalAmount = cursor.getInt(2);
        RemainingAmount = cursor.getInt(3);
        OrderDate = cursor.getString(4);
        Deadline = cursor.getString(5);
        Status = cursor.getString(6);
    }

    public static String[] getColumnsName() {
        return COLUMNS_NAME;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        CustomerId = customerId;
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

    // DB methods
    static public void updateOrderStatus(Integer orderId, String status) {
        SQLiteDatabase db = QueryHandler.getWriteableDB();

        ContentValues cv = new ContentValues();
        cv.put(COLUMNS_NAME[7], status);

        db.update(COLUMNS_NAME[0], cv, String.format("%s = ?", COLUMNS_NAME[1]), new String[]{Integer.toString(orderId)});
    }
}
