package com.tripidevs.swoly.maxes;

/**
 * Created by user on 1/10/2017.
 */

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

    public void setLiftMax(int liftMax) {
        this.liftMax = liftMax;
    }

    public String getLiftName() {
        return liftName;
    }

    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }
}