package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    Context myContext = null;


    public DBHelper(@Nullable Context context) {
        super(context, "Main.db", null, 1);

        myContext = context;
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
        MeasurementsDB measurementsDB = new MeasurementsDB(myContext);
        ArrayList<TailorMeasurements> tailorMeasurements = measurementsDB.getAllTailorMeasurements();
        String customerMeasurementsSql = "CREATE TABLE CustomerMeasurementsTable (Id Integer PRIMARY KEY AUTOINCREMENT, CustomerID Integer";

        for(int i =  0; i < tailorMeasurements.size(); ++i) {
            TailorMeasurements tailorMeasurements1 = tailorMeasurements.get(i);

            customerMeasurementsSql += ", " + tailorMeasurements1.getName() + " ";

            if(tailorMeasurements1.getType() == "Numeric") {
                customerMeasurementsSql += "Integer";
            } else {
                customerMeasurementsSql += "Text";
            }
        }

        customerMeasurementsSql += ")";
        db.execSQL(customerMeasurementsSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // CustomerMeasurementsTable
        MeasurementsDB measurementsDB = new MeasurementsDB(myContext);
        ArrayList<TailorMeasurements> tailorMeasurements = measurementsDB.getAllTailorMeasurements();


        String customerMeasurementsSql = "ALTER TABLE CustomerMeasurementsTable ";


    }


    public ArrayList<String> getAllColumns() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "PRAGMA table_info(CustomerMeasurementsTable)";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                

            }while(cursor.moveToNext());
        }


        return new ArrayList<String>();
    }


}
