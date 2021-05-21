package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;

public class Table {
    static public String TABLE_NAME = "";
    static public final String ID = "Id";

    Integer Id;

    static public Table newObject(Cursor cursor) {
        return new Table();
    }

    static public ContentValues getContentValues(Table table) {
        return new ContentValues();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
