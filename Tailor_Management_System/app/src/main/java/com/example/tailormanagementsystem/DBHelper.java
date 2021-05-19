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
        super(context, "Main.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CustomerTable
        String customerQuery = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Text, %s Text, %s Text, %s Text, %s Text, %s Integer)", Customer.TABLE_NAME, Customer.ID, Customer.NAME, Customer.PHONE_NUMBER, Customer.GENDER, Customer.ADDRESS, Customer.EMAIL, Customer.RELATED_CUSTOMER_ID);
        db.execSQL(customerQuery);

        // OrderTable
        String orderQuery = "CREATE TABLE OrderTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerId Integer, OrderDate Date, Deadline Date, Status Text, TotalAmount Integer, RemainingAmount Integer, FOREIGN KEY (CustomerId) REFERENCES CustomerTable (Id))";
        db.execSQL(orderQuery);

        // ItemTable
        // e.g ShalwarKameez, Pent Coat etc.
        String itemSql = "CREATE TABLE ItemTable (Id Integer PRIMARY KEY AUTOINCREMENT, Name String, Price Integer)";
        db.execSQL(itemSql);

        // OrderItemTable
        String orderItemTable = "CREATE TABLE OrderItemTable (OrderId Integer, ItemId Integer, Quantity Integer, FOREIGN KEY (OrderId) REFERENCES OrderTable (Id), FOREIGN KEY (ItemId) REFERENCES ItemTable (Id))";
        db.execSQL(orderItemTable);

        // ReceiptTable
        String receiptSql = "CREATE TABLE ReceiptTable (Id Integer PRIMARY KEY AUTOINCREMENT,OrderId Integer, PaymentDate Date, Amount Integer, FOREIGN KEY (OrderId) REFERENCES OrderTable (Id))";
        db.execSQL(receiptSql);

        // MeasurementsTable
        String measurementsSql = "CREATE TABLE MeasurementsTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerId Integer, Length Real, Shoulder Real, Neck Real, Chest Real, Waist Real, Hip Real, Ghera Real, Type_of_Ghera Text, Arm Real, Moda Real, Shalwar_Length Real, Shalwar_Ghera Real, Shalwar_Pencha Real, No_of_Front_Pockets Integer, No_of_Side_Pockets Integer, No_of_Shalwar_Pockets Integer, Salaai_Type Text, FOREIGN KEY (CustomerId) REFERENCES CustomerTable (Id))";
        db.execSQL(measurementsSql);

        // CheckBoxTable
        String checkBoxSql = "CREATE TABLE CheckBoxTable (Id Integer PRIMARY KEY AUTOINCREMENT, MeasurementName Text, MeasurementsOptions Text)";
        db.execSQL(checkBoxSql);

        // Inserting Data
        String insertCheckBoxSql = "INSERT INTO CheckBoxTable (MeasurementName, MeasurementsOptions) Values ('Type_of_Ghera', 'Goal_Choros'), ('Salaai_Type', 'Single_Double_Triple')";
        db.execSQL(insertCheckBoxSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public void dropColumnFromMeasurementsTable(String columnName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE MeasurementsTable DROP COLUMN " + columnName);
    }

    public void addColumnInMeasurementsTable(String columnName, String columnType) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE MeasurementsTable ADD COLUMN " + columnName + " " + columnType);
    }


    public ArrayList<Pair<String, String>> getMeasurementsTableColumns() {
        ArrayList<Pair<String, String>> columns = new ArrayList<Pair<String, String>>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("PRAGMA table_info(MeasurementsTable)", null);

        if(cursor.moveToFirst()) {
            do {
                columns.add(new Pair<String, String>(cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }

        return columns;
    }

}
