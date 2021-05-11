package com.example.tailormanagementsystem;

public class TailorMeasurements {

    int Id;
    String Name;
    String Type;
    String CheckBoxNames;

    public TailorMeasurements(int id, String name, String type, String checkBoxNames) {
        Id = id;
        Name = name;
        Type = type;
        CheckBoxNames = checkBoxNames;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCheckBoxNames() {
        return CheckBoxNames;
    }

    public void setCheckBoxNames(String checkBoxNames) {
        CheckBoxNames = checkBoxNames;
    }
}
