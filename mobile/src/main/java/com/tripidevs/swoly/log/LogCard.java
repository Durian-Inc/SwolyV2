package com.tripidevs.swoly.log;

import java.util.ArrayList;

/**
 * Created by maver on 1/17/2017.
 */

public class LogCard {
    private String m_title;
    private ArrayList<Integer> m_arrayList;

    public LogCard(String name){
        this.m_title = name;
    }

    public void addItem(final int item){
        m_arrayList.add(item);
    }

    public ArrayList<Integer> getProgress(){
        return m_arrayList;
    }


}
