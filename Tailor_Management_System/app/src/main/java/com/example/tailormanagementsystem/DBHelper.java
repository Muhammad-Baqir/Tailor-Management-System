package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Main.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CustomerTable
        String customerQuery = "CREATE TABLE CustomerTable (Id Integer PRIMARY KEY AUTOINCREMENT, Name Text, PhoneNumber Text, Gender Text, Address Text, Email Text, RelatedCustomerID Integer)";
        db.execSQL(customerQuery);

        // OrderTable
        String orderQuery = "CREATE TABLE OrderTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerID Integer, OrderDate Date, Deadline Date, Status Text, TotalAmount Integer, RemainingAmount Integer)";
        db.execSQL(orderQuery);

        // OrderItemTable
        String orderItemTable = "CREATE TABLE OrderItemTable (OrderID Integer, ItemID Integer, Quantity Integer)";
        db.execSQL(orderItemTable);

        // ItemTable
        String itemSql = "CREATE TABLE ItemTable (Id Integer PRIMARY KEY AUTOINCREMENT, Name String, Price Integer)";
        db.execSQL(itemSql);

        // ReceiptTable
        String receiptSql = "CREATE TABLE ReceiptTable (Id Integer PRIMARY KEY AUTOINCREMENT,OrderID Integer, PaymentDate Date, Amount Integer)";
        db.execSQL(receiptSql);

        // CustomerMeasurementsTable
        String customerMeasurementsSql = "CREATE TABLE CustomerMeasurementsTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerID Integer)";
        db.execSQL(customerMeasurementsSql);

        // CheckBoxTable
        String checkBoxSql = "CREATE TABLE CheckBoxTable (Id Integer PRIMARY KEY AUTOINCREMENT, MeasurementName Text, CheckBoxNames Text)";
        db.execSQL(checkBoxSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void dropColumnFromCustomerMeasurements(String columnName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE CustomerMeasurementsTable DROP COLUMN " + columnName);
    }

    public void addColumnInCustomerMeasurements(String columnName, String columnType) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE CustomerMeasurementsTable ADD COLUMN " + columnName + " " + columnType);
    }


    public ArrayList<Pair<String, String>> getColumns() {
        ArrayList<Pair<String, String>> columns = new ArrayList<Pair<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("PRAGMA table_info(CustomerMeasurementsTable)", null);

        if(cursor.moveToFirst()) {
            do {
                columns.add(new Pair<String, String>(cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }

        return columns;
    }

}
