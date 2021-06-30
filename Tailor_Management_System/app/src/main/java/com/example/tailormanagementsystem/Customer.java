package com.example.tailormanagementsystem;

import android.database.Cursor;

import java.lang.reflect.InvocationTargetException;

public class Customer extends Table {
    static public final String[] COLUMNS_NAME = new String[] {"CustomerTable", "Id", "Name", "PhoneNumber", "Gender", "Address", "Email", "RelatedCustomerId"};

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

    public Customer(Cursor cursor) {
        Id = cursor.getInt(0);
        Name = cursor.getString(1);
        PhoneNumber = cursor.getString(2);
        Gender = cursor.getString(3);
        Address = cursor.getString(4);
        Email = cursor.getString(5);
        RelatedCustomerId = cursor.getInt(6);
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

}
