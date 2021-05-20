package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class QueryHandler {

    static public <T extends Table> T get(Class<T> c, SQLiteDatabase db, int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        T result = c.newInstance();
        String TABLE_NAME = (String) c.getDeclaredField("TABLE_NAME").get(null);

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE Id = ?", TABLE_NAME), new String[]{Integer.toString(id)});

        if(cursor.moveToNext()) {
            result = (T) c.getMethod("newObject", Cursor.class).invoke(null, cursor);
        }

        return result;
    }

    static public <T extends Table> ArrayList<T> getAll(Class<T> c, SQLiteDatabase db) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        ArrayList<T> result = new ArrayList<>();
        String TABLE_NAME = (String) c.getDeclaredField("TABLE_NAME").get(null);

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);

        if(cursor.moveToFirst()) {
            do {
                Method method = c.getDeclaredMethod("newObject", Cursor.class);
                result.add((T) method.invoke(null, cursor));
            }while (cursor.moveToNext());
        }
        return result;
    }

    static public <T extends Table> boolean add(SQLiteDatabase db, T item) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = item.getClass();
        Method getContentValues = c.getDeclaredMethod("getContentValues", c);
        ContentValues cv = (ContentValues) getContentValues.invoke(null, item);
        String TABLE_NAME = (String) c.getDeclaredField("TABLE_NAME").get(null);

        return db.insert(TABLE_NAME, null, cv) != -1;
    }

    static public <T extends Table> boolean update(SQLiteDatabase db, T item) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class c = item.getClass();
        Method getContentValues = c.getDeclaredMethod("getContentValues", c);
        ContentValues cv = (ContentValues) getContentValues.invoke(null, item);
        String TABLE_NAME = (String) c.getDeclaredField("TABLE_NAME").get(null);

        return  db.update(TABLE_NAME, cv, "Id = ?", new String[]{Integer.toString(item.getId())}) != -1;
    }

    static public <T extends Table> boolean delete(Class<T> c, SQLiteDatabase db, int id) throws NoSuchFieldException, IllegalAccessException {
        String TABLE_NAME = (String) c.getDeclaredField("TABLE_NAME").get(null);
        return db.delete(TABLE_NAME, "Id = ?", new String[]{Integer.toString(id)}) != -1;
    }

}
