package com.example.tailormanagementsystem;

public class Customer {

    int Id;
    String Name;
    String PhoneNumber;
    String Gender;
    String Address;
    String Email;
    int RelatedCustomerID;

    public Customer(int id, String name,String phoneNumber, String gender,String address,String email,int relatedCustomerID) {
        Id = id;
        Name = name;
        PhoneNumber=phoneNumber;
        Gender=gender;
        Address=address;
        Email=email;
        RelatedCustomerID=relatedCustomerID;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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

    public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }
    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) { Gender = gender; }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) { Address = address; }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) { Email = email; }
    public int getRelatedCustomerID() {
        return RelatedCustomerID;
    }

    public void setRelatedCustomerID(int relatedCustomerID) { RelatedCustomerID = relatedCustomerID; }
}
