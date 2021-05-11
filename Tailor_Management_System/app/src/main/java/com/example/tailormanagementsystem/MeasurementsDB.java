package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MeasurementsDB extends SQLiteOpenHelper {
    final String TABLE_NAME = "TableMeasurementsTable";

    public MeasurementsDB(@Nullable Context context) {
        super(context, "MeasurementsDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TailorMeasurementsTable
        String tailorQuery = "CREATE TABLE " + TABLE_NAME +" (Id Integer PRIMARY KEY AUTOINCREMENT, Name Text, Type Text, CheckBoxNames Text)";
        db.execSQL(tailorQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addMeasurement(TailorMeasurements tailorMeasurements) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Hash map, as we did in bundles
        ContentValues cv = new ContentValues();

        cv.put("Name", tailorMeasurements.getName());
        cv.put("Type", tailorMeasurements.getType());
        cv.put("CheckBoxNames", tailorMeasurements.getCheckBoxNames());

        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1) { return false; }
        else{return true;}
    }

    public ArrayList<TailorMeasurements> getAllMeasurements() {
        ArrayList<TailorMeasurements> tailorMeasurements = new ArrayList<TailorMeasurements>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        if(cursor.moveToFirst()) {
            do {
                tailorMeasurements.add(new TailorMeasurements(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        return tailorMeasurements;
    }
}
