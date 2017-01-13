package com.tripidevs.swoly;

public class DatabaseItem {

    private int _id;
    private int _value;

    public DatabaseItem() {

    }

    public DatabaseItem(int id, int _value) {
        this._id = id;
        this._value = _value;
    }

    public DatabaseItem(int _value) {
        this._value = _value;
    }

    public int getID(){
        return this._id;
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
