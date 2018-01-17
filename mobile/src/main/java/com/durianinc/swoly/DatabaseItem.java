package com.durianinc.swoly;

public class DatabaseItem {

    private int _id;
    private int _value;

    public DatabaseItem() {

    }

    public DatabaseItem(int _value) {
        this._value = _value;
    }

    public void setID(int id) {
        this._id = id;
    }

    // getting value
    public int getValue(){
        return this._value;
    }

    // setting value
    public void setValue(int value) {
        this._value = value;
    }
}
