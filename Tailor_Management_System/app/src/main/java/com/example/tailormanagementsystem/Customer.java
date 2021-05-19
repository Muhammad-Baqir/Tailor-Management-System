package com.example.tailormanagementsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Customer {
    static public final String TABLE_NAME = "CustomerTable";
    static public final String ID = "Id";
    static public final String NAME = "Name";
    static public final String PHONE_NUMBER = "PhoneNumber";
    static public final String GENDER = "Gender";
    static public final String ADDRESS = "Address";
    static public final String EMAIL = "Email";
    static public final String RELATED_CUSTOMER_ID = "RelatedCustomerId";

    Integer Id;
    String Name;
    String PhoneNumber;
    String Gender;
    String Address;
    String Email;
    Integer RelatedCustomerId;

    public Customer(Integer id, String name,String phoneNumber, String gender,String address,String email,Integer relatedCustomerId) {
        Id = id;
        Name = name;
        PhoneNumber=phoneNumber;
        Gender=gender;
        Address=address;
        Email=email;
        RelatedCustomerId=relatedCustomerId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getRelatedCustomerId() {
        return RelatedCustomerId;
    }

    public void setRelatedCustomerId(Integer relatedCustomerId) {
        RelatedCustomerId = relatedCustomerId;
    }

    // DBase Methods

    static public ArrayList<Customer> getAll(SQLiteDatabase db) {
        ArrayList<Customer> result = new ArrayList<>();

        Cursor cursor = db.rawQuery(String.format("SELECT * FROM %s", TABLE_NAME), null);

        if(cursor.moveToFirst()) {
            do {
                result.add(new Customer(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
            }while (cursor.moveToNext());
        }

        return result;
    }

    static public boolean add(SQLiteDatabase db, Customer customer) {
        return db.insert(TABLE_NAME, null, getContentValues(customer)) != -1;
    }

    static public boolean update(SQLiteDatabase db, Customer customer) {
        return  db.update(TABLE_NAME, getContentValues(customer), "Id = ?", new String[]{Integer.toString(customer.Id)}) != -1;
    }

    static public boolean delete(SQLiteDatabase db, int id) {
        return db.delete(TABLE_NAME, "Id = ?", new String[]{Integer.toString(id)}) != -1;
    }

    static ContentValues getContentValues(Customer customer) {
        ContentValues cv = new ContentValues();

        cv.put(NAME, customer.Name);
        cv.put(PHONE_NUMBER, customer.PhoneNumber);
        cv.put(GENDER, customer.Gender);
        cv.put(ADDRESS, customer.Address);
        cv.put(EMAIL, customer.Email);
        cv.put(RELATED_CUSTOMER_ID, customer.RelatedCustomerId);

        return cv;
    }
}
