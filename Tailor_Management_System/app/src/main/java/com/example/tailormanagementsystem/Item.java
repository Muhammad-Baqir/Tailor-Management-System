package com.example.tailormanagementsystem;

public class Item {
    int Id;
    String Name;
    int Price;

    public Item(int id, String name,int price) {
        Id = id;
        Name = name;
        Price=price;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
