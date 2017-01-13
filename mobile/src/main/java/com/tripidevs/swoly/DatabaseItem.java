package com.tripidevs.swoly;

public class DatabaseItem {

    private int _id;
    private String _item;
    private int _value;

    public DatabaseItem() {

    }

    public DatabaseItem(int id, String item, int _value) {
        this._id = id;
        this._item = item;
        this._value = _value;
    }

    public DatabaseItem(String item, int _value) {
        this._item = item;
        this._value = _value;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getItem(){
        return this._item;
    }

    // setting item name
    public void setItem(String item){
        this._item = item;
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
