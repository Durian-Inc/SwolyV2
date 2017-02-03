package com.tripidevs.swoly.progress;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by maver on 1/17/2017.
 */

public class ProgressCard {
    private String m_title;
    private ArrayList<Integer> m_arrayList = new ArrayList<>();

    public ProgressCard(String name){
        this.m_title = name;
    }

    public void addItem(final int item){
        m_arrayList.add(item);
    }

    public ArrayList<Integer> getProgress(){
        return m_arrayList;
    }

    public String getTitle(){
        return this.m_title;
    }

}
