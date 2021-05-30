package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;

public class Item extends Table {
    static public final String[] COLUMNS_NAME = new String[] {"ItemTable", "Id",  "Name", "Price"};

    String Name;
    Integer Price;

    public Item(Integer id, String name, Integer price) {
        Id = id;
        Name = name;
        Price=price;
    }

    public Item(Cursor cursor) {
        Id = cursor.getInt(0);
        Name = cursor.getString(1);
        Price = cursor.getInt(2);
    }

    public static String[] getColumnsName() {
        return COLUMNS_NAME;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
}
