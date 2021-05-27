package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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
        String customerQuery = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Text, %s Text, %s Text, %s Text, %s Text, %s Integer)", Customer.COLUMNS_NAME[0], Customer.COLUMNS_NAME[1], Customer.COLUMNS_NAME[2], Customer.COLUMNS_NAME[3], Customer.COLUMNS_NAME[4], Customer.COLUMNS_NAME[5], Customer.COLUMNS_NAME[6], Customer.COLUMNS_NAME[7]);
        db.execSQL(customerQuery);

        // OrderTable
        //String orderQuery = "CREATE TABLE OrderTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerId Integer, TotalAmount Integer, RemainingAmount Integer, OrderDate Date, Deadline Date, Status Text, FOREIGN KEY (CustomerId) REFERENCES CustomerTable (Id))";
        String orderQuery = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Integer, %s Integer, %s Integer,%s Date, %s Date, %s Text, FOREIGN KEY (%s) REFERENCES %s (%s))", Order.COLUMNS_NAME[0], Order.COLUMNS_NAME[1], Order.COLUMNS_NAME[2], Order.COLUMNS_NAME[3], Order.COLUMNS_NAME[4], Order.COLUMNS_NAME[5], Order.COLUMNS_NAME[6], Order.COLUMNS_NAME[7],Order.COLUMNS_NAME[2],Customer.COLUMNS_NAME[0],Customer.COLUMNS_NAME[1]);
        db.execSQL(orderQuery);

        // ItemTable
        // e.g ShalwarKameez, Pent Coat etc.
        //String itemSql = "CREATE TABLE ItemTable (Id Integer PRIMARY KEY AUTOINCREMENT, Name String, Price Integer)";
        String itemSql = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Text, %s Integer)", Item.COLUMNS_NAME[0], Item.COLUMNS_NAME[1], Item.COLUMNS_NAME[2], Item.COLUMNS_NAME[3]);
        db.execSQL(itemSql);

        // OrderItemTable
        //String orderItemTable = "CREATE TABLE OrderItemTable (OrderId Integer, ItemId Integer, Quantity Integer, FOREIGN KEY (OrderId) REFERENCES OrderTable (Id), FOREIGN KEY (ItemId) REFERENCES ItemTable (Id))";
        String orderItemTable = String.format("CREATE TABLE %s (%s Integer, %s Integer, %s Integer, FOREIGN KEY (%s) REFERENCES %s (%s),FOREIGN KEY (%s) REFERENCES %s (%s))", OrderItem.COLUMNS_NAME[0], OrderItem.COLUMNS_NAME[2], OrderItem.COLUMNS_NAME[3], OrderItem.COLUMNS_NAME[4], OrderItem.COLUMNS_NAME[2],Order.COLUMNS_NAME[0],Order.COLUMNS_NAME[1],OrderItem.COLUMNS_NAME[3],Item.COLUMNS_NAME[0], Item.COLUMNS_NAME[1]);
        db.execSQL(orderItemTable);

        // ReceiptTable
       // String receiptSql = "CREATE TABLE ReceiptTable (Id Integer PRIMARY KEY AUTOINCREMENT,OrderId Integer, PaymentDate Date, Amount Integer, FOREIGN KEY (OrderId) REFERENCES OrderTable (Id))";
        String receiptSql = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Integer, %s Date, %s Integer, FOREIGN KEY (%s) REFERENCES %s (%s))", Receipt.COLUMNS_NAME[0], Receipt.COLUMNS_NAME[1], Receipt.COLUMNS_NAME[2], Receipt.COLUMNS_NAME[3], Receipt.COLUMNS_NAME[4], Receipt.COLUMNS_NAME[2], Order.COLUMNS_NAME[0], Order.COLUMNS_NAME[1]);
        db.execSQL(receiptSql);

        // MeasurementsTable
        String measurementsSql = "CREATE TABLE MeasurementsTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerId Integer, Length Real, Shoulder Real, Neck Real, Chest Real, Waist Real, Hip Real, Ghera Real, Type_of_Ghera Text, Arm Real, Moda Real, Shalwar_Length Real, Shalwar_Ghera Real, Shalwar_Pencha Real, No_of_Front_Pockets Integer, No_of_Side_Pockets Integer, No_of_Shalwar_Pockets Integer, Salaai_Type Text, FOREIGN KEY (CustomerId) REFERENCES CustomerTable (Id))";
        //String orderQuery = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Integer, %s Integer, %s Integer,%s Date, %s Date, %s Text, FOREIGN KEY (%s) REFERENCES %s (%s))", Order.COLUMNS_NAME[0], Order.COLUMNS_NAME[1], Order.COLUMNS_NAME[2], Order.COLUMNS_NAME[3], Order.COLUMNS_NAME[4], Order.COLUMNS_NAME[5], Order.COLUMNS_NAME[6], Order.COLUMNS_NAME[7],Order.COLUMNS_NAME[2],Customer.COLUMNS_NAME[0],Customer.COLUMNS_NAME[1]);
        db.execSQL(measurementsSql);

        // CheckBoxTable
        String checkBoxSql = "CREATE TABLE CheckBoxTable (Id Integer PRIMARY KEY AUTOINCREMENT, MeasurementName Text, MeasurementsOptions Text)";
        //String orderQuery = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Integer, %s Integer, %s Integer,%s Date, %s Date, %s Text, FOREIGN KEY (%s) REFERENCES %s (%s))", Order.COLUMNS_NAME[0], Order.COLUMNS_NAME[1], Order.COLUMNS_NAME[2], Order.COLUMNS_NAME[3], Order.COLUMNS_NAME[4], Order.COLUMNS_NAME[5], Order.COLUMNS_NAME[6], Order.COLUMNS_NAME[7],Order.COLUMNS_NAME[2],Customer.COLUMNS_NAME[0],Customer.COLUMNS_NAME[1]);
        db.execSQL(checkBoxSql);

        // Inserting Data
        String insertCheckBoxSql = "INSERT INTO CheckBoxTable (MeasurementName, MeasurementsOptions) Values ('Type_of_Ghera', 'Goal_Choros'), ('Salaai_Type', 'Single_Double_Triple')";
       // String orderQuery = String.format("CREATE TABLE %s (%s Integer PRIMARY KEY AUTOINCREMENT, %s Integer, %s Integer, %s Integer,%s Date, %s Date, %s Text, FOREIGN KEY (%s) REFERENCES %s (%s))", Order.COLUMNS_NAME[0], Order.COLUMNS_NAME[1], Order.COLUMNS_NAME[2], Order.COLUMNS_NAME[3], Order.COLUMNS_NAME[4], Order.COLUMNS_NAME[5], Order.COLUMNS_NAME[6], Order.COLUMNS_NAME[7],Order.COLUMNS_NAME[2],Customer.COLUMNS_NAME[0],Customer.COLUMNS_NAME[1]);
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

    public String getRadioButtonNames(String measurementName) {
        String result = "";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT MeasurementsOptions  FROM CheckBoxTable WHERE MeasurementName = ?", new String[]{measurementName});

        if(cursor.moveToFirst()) {
            result = cursor.getString(0);
        }

        return result;
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
