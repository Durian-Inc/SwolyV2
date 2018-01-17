package com.durianinc.swoly.maxes;

public class MaxesCard {
    private String liftName;
    private int liftMax;

    public MaxesCard(String name, int max){
        this.liftMax = max;
        this.liftName = name;
    }
    public int getLiftMax() {
        return liftMax;
    }

    public String getLiftName() {
        return liftName;
    }

}