package com.example.tailormanagementsystem;


public class OrderItem {
    int OrderID;
    int ItemID;
    int Quantity;

    public OrderItem(int orderID, int itemID, int quantity) {
        OrderID=orderID;
        ItemID=itemID;
        Quantity=quantity;
    }
    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
