package com.tripidevs.swoly.Maxes;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tripidevs.swoly.R;

import java.util.ArrayList;

/**
 * Created by Innocent on 1/10/2017.
 */

public class MaxesActivity extends Activity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MaxesCard> list = new ArrayList<>();
    String[] lifts;
    int[] maxes = {500, 135, 225, 650, 666, 999};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maxes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        lifts = getResources().getStringArray(R.array.liftNames);
        int count = 0;
        for(String Lift : lifts){
            MaxesCard newMax = new MaxesCard(Lift,
                    String.valueOf(maxes[count]));
            count++;
            list.add(newMax);
        }

        recyclerView = (RecyclerView) findViewById(R.id.maxesRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new MaxesAdapter(list);
        recyclerView.setAdapter(adapter);
    }


}
