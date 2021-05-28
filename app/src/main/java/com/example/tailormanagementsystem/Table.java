package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;

public class Table {
    static public final String[] COLUMNS_NAME = new String[]{"", "Id"};

    Integer Id;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
