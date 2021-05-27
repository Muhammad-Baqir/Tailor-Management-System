package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class QueryHandler {
    static public <T extends Table> T get(Class<T> c, SQLiteDatabase db, int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        T result = c.newInstance();
        String[] COLUMNS_NAME = (String[]) c.getDeclaredField("COLUMNS_NAME").get(null);

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE Id = ?", COLUMNS_NAME[0]), new String[]{Integer.toString(id)});

        if(cursor.moveToNext()) {
            result =  c.getConstructor(new Class[]{Cursor.class}).newInstance(cursor);
        }

        return result;
    }

    static public <T extends Table> ArrayList<T> getAll(Class<T> c, SQLiteDatabase db) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        ArrayList<T> result = new ArrayList<>();
        String[] COLUMNS_NAME = (String[]) c.getDeclaredField("COLUMNS_NAME").get(null);

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", COLUMNS_NAME[0]), null);

        if(cursor.moveToFirst()) {
            do {
                result.add(c.getConstructor(new Class[]{Cursor.class}).newInstance(cursor));
            }while (cursor.moveToNext());
        }
        return result;
    }

    static public <T extends Table> boolean add(SQLiteDatabase db, T item) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = item.getClass();
        ContentValues cv = getContentValues(item);
        String[] COLUMNS_NAME = (String[]) c.getDeclaredField("COLUMNS_NAME").get(null);

        return db.insert(COLUMNS_NAME[0], null, cv) != -1;
    }

    static public <T extends Table> boolean update(SQLiteDatabase db, T item) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class c = item.getClass();
        ContentValues cv = getContentValues(item);
        String[] COLUMNS_NAME = (String[]) c.getDeclaredField("COLUMNS_NAME").get(null);

        return  db.update(COLUMNS_NAME[0], cv, "Id = ?", new String[]{Integer.toString(item.getId())}) != -1;
    }

    static public <T extends Table> boolean delete(Class<T> c, SQLiteDatabase db, int id) throws NoSuchFieldException, IllegalAccessException {
        String[] COLUMNS_NAME = (String[]) c.getDeclaredField("COLUMNS_NAME").get(null);
        return db.delete(COLUMNS_NAME[0], "Id = ?", new String[]{Integer.toString(id)}) != -1;
    }


    static <T> ContentValues getContentValues(T item) throws IllegalAccessException, NoSuchFieldException {
        ContentValues cv = new ContentValues();
        Class c = item.getClass();
        String[] COLUMNS_NAME = (String[]) c.getDeclaredField("COLUMNS_NAME").get(null);

        for(int i = 0; i < COLUMNS_NAME.length - 2; ++i) {
            Log.d("Item", COLUMNS_NAME[i+2] + " : " + c.getDeclaredField(COLUMNS_NAME[i+2]).get(item).toString());
            cv.put(COLUMNS_NAME[i + 2],  c.getDeclaredField(COLUMNS_NAME[i+2]).get(item).toString());
        }

        return cv;
    }

}
