package com.tripidevs.swoly.Maxes;

/**
 * Created by user on 1/10/2017.
 */

public class MaxesCard {
    private String liftName;
    private String liftMax;

    public MaxesCard(String name, String max){
        this.liftMax = max;
        this.liftName = name;
    }
    public String getLiftMax() {
        return liftMax;
    }

    public void setLiftMax(String liftMax) {
        this.liftMax = liftMax;
    }

    public String getLiftName() {
        return liftName;
    }

    public void setLiftName(String liftName) {
        this.liftName = liftName;
    }
}
