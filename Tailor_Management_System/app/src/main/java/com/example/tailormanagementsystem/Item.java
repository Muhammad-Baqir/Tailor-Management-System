package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;

public class Item extends Table {
    static public final String TABLE_NAME = "ItemTable";
    static public final String NAME = "Name";
    static public final String PRICE = "Price";

    String Name;
    Integer Price;

    public Item(Integer id, String name,Integer price) {
        Id = id;
        Name = name;
        Price=price;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
    public int getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    static public Table newObject(Cursor cursor) {
        return new Item(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
    }

    static public ContentValues getContentValues(Item item) {
        ContentValues cv = new ContentValues();

        cv.put(NAME, item.Name);
        cv.put(PRICE, item.Price);

        return cv;
    }
}
