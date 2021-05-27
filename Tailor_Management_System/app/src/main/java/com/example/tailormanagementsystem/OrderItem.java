package com.example.tailormanagementsystem;


import android.database.Cursor;

public class OrderItem extends Table {
    static public final String[] COLUMNS_NAME = new String[] {"OrderItemTable", "Id", "OrderId", "ItemId", "Quantity"};

    Integer OrderID;
    Integer ItemID;
    Integer Quantity;

    public OrderItem(Integer id, Integer orderID, Integer itemID, Integer quantity) {
        Id = id;
        OrderID = orderID;
        ItemID = itemID;
        Quantity = quantity;
    }

    public OrderItem(Cursor cursor) {
        Id = cursor.getInt(0);
        OrderID = cursor.getInt(1);
        ItemID = cursor.getInt(2);
        Quantity = cursor.getInt(3);
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

    public Integer getItemID() {
        return ItemID;
    }

    public void setItemID(Integer itemID) {
        ItemID = itemID;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }
}
